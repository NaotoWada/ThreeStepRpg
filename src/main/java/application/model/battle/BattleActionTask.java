package application.model.battle;

import application.ctrl.TopMenuController;
import application.model.battle.ProgressBarHelper.STS;
import application.model.battle.sub.BattleContinueJudge;
import application.model.battle.sub.MergeParty;
import application.model.battle.sub.TransPict;
import application.model.battle.wave.WaveHelper;
import application.model.exp.ExperienceManager;
import application.model.item.BattleItems;
import application.thread.ThreadUtil;
import dto.achieve.AchieveManager;
import dto.achieve.enums.EVENT;
import dto.chara.abs.Characters;
import dto.chara.effect.ActionEffect;
import dto.chara.effect.EffectHelper;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;

public class BattleActionTask extends Task<Void> {

    @Override
    protected Void call() throws Exception {

        AchieveManager.achieveAllDebug();

        // WAVEのリセット
        WaveHelper.reset();

        // 初回のHP,MPバーの設定
        Platform.runLater(() -> ProgressBarHelper.update(STS.HP, MergeParty.loadActionCharas()));
        Platform.runLater(() -> ProgressBarHelper.update(STS.MP, MergeParty.loadActionCharas()));

        ThreadUtil.sleep(500L);
        System.out.println("戦闘開始!!!");

        // ターンのリセット
        int turnCnt = 1;
        StringProperty turnProp = BattleService.getInstance().textTurnProperty();
        setTurnProperty(turnProp, turnCnt);

        // キャラのロード 敵のみアクション中でリロードしてマージしているのがちょっと面倒くさい
        Characters[] charas = MergeParty.loadActionCharas();

        // 毎回インスタンスを使いまわしているのにアイテムが重複しない。なぜ
        BattleItems.affectItems(charas);

        while (true) {

            if (this.isCancelled()) {
                break;
            }

            executeAction(charas);

            if (BattleContinueJudge.isPlayerAllDead(charas)) {
                // 味方の全滅確認
                AchieveManager.achieve(EVENT.ALL_PLAYER_KILLED);
                break;
            }

            if (BattleContinueJudge.canNextWave(charas)) {
                // 倒した相手のEXPを加算する
                ExperienceManager.addExp(charas);

                // 次WAVEに遷移
                WaveHelper.inclement();
                WaveHelper.changeEnemy();
                AchieveManager.achieve(EVENT.FIRST_KILL);
                charas = MergeParty.merge(charas);
                turnCnt = 1;
                setTurnProperty(turnProp, turnCnt);
                continue;
            }

            if (turnCnt > 99) {
                // 閾値ターンを超えると自動終了
                break;
            }
            turnCnt++;
            setTurnProperty(turnProp, turnCnt);
        }

        Platform.runLater(() -> TopMenuController.getInstance().changeThenShow());

        return null;
    }

    private void setTurnProperty(StringProperty turnProp, int turnCnt) {
        Platform.runLater(() -> turnProp.setValue(String.valueOf(turnCnt)));
    }

    private void executeAction(Characters[] charas) {
        for (Characters chr : charas) {
            // アクションを状況に応じてランダムで設定し実行
            chr.decideAction();
            ActionEffect effect = chr.action(charas);

            // TODO : アクションの戻りでエフェクトを表示していく
            Platform.runLater(() -> EffectHelper.dispValue(effect));
            Platform.runLater(() -> EffectHelper.dispEffect(effect));

            // HP,MPバーの反映
            Platform.runLater(() -> ProgressBarHelper.update(STS.HP, charas));
            Platform.runLater(() -> ProgressBarHelper.update(STS.MP, charas));
            TransPict.toGrave(charas);

            if (BattleContinueJudge.canNextWave(charas)) {
                // 次のウェーブに行ける場合、残りのキャラの行動は行わない仕様
                return;
            }
            ThreadUtil.sleep(300L);
        }
    }
}
