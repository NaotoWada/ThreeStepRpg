package application.model.battle.wave;

import application.model.battle.BattleService;
import dto.chara.enums.EnemyEnums.ENEMY;
import dto.chara.manage.EnemyManager;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import resource.img.PaneMonsterAnimeManager;

public class WaveHelper {

    private static int _Rank = 0;
    private static int _Wave = 1;
    private static final String _InitWave = "1";
    private static final String _InitRank = "0";

    public static void inclement() throws IllegalAccessException {
        StringProperty textStairsProperty = BattleService.getInstance().textStairsProperty();

        int nowNum = Integer.parseInt(textStairsProperty.getValue());

        if (nowNum != _Wave) {
            throw new IllegalAccessException("Waveが不正な値になっています。");
        }

        _Wave++;
        progresRank(nowNum);
        String val = String.valueOf(++nowNum);

        Platform.runLater(() -> textStairsProperty.setValue(val));
    }

    private static void progresRank(int nowNum) {
        // x1の階層の時にランクを上げていく
        System.out.println("nowNum:" + nowNum);
        if (((nowNum - 1) / 10) > _Rank) {
            _Rank++;
            Platform.runLater(() -> BattleService.getInstance().getTextRankProperty()
                    .set(String.valueOf(_Rank)));
            System.out.println("rank:" + _Rank);
        }
    }

    public static void changeEnemy() {
        if (isBossWave()) {

            changeEnemy(MobStorage.selectBoss(_Rank));
        } else if (isMidBossWave()) {

            changeEnemy(MobStorage.selectMidBoss(_Rank));
        } else {

            changeEnemy(MobStorage.selectRndmEnemy(_Rank));
        }
    }

    private static boolean isMidBossWave() {
        return _Wave % 5 == 0;
    }

    private static boolean isBossWave() {
        return _Wave % 10 == 0;
    }

    private static void changeEnemy(ENEMY en) {
        EnemyManager.setEnemy(en, 0);
        ImageView monsImg = PaneMonsterAnimeManager.getInstance().newImage(en);
        Platform.runLater(() -> changePane(monsImg));
    }

    private static void changePane(ImageView monsImg) {
        // javafxスレッドでないと、clear処理やadd処理できない
        Pane monsPane = BattleService.get_MonsBattlePane();
        monsPane.getChildren().clear();
        monsPane.getChildren().add(monsImg);
    }

    public static void reset() {
        StringProperty textStairsProperty = BattleService.getInstance().textStairsProperty();
        Platform.runLater(() -> textStairsProperty.setValue(_InitWave));
        StringProperty textRankProperty = BattleService.getInstance().textRankProperty();
        Platform.runLater(() -> textRankProperty.setValue(_InitRank));
        _Rank = 0;
        _Wave = 1;
    }
}
