package dto.chara.effect;

import application.model.battle.BattleService;
import application.model.battle.sub.DamageValueCalclator;
import dto.chara.abs.IAction.EFFECT;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import resource.img.ImageAnimationView;
import resource.img.PaneAnimationManager;

public class EffectHelper {

    public static void dispValue(ActionEffect effect) {
        EFFECT type = effect.getEffect();
        switch (type) {
            case HEAL:
                break;
            default:
                int effectValue = effect.getEffectValue();
                int target = effect.getTarget();
                Platform.runLater(() -> dispDamage(target, effectValue));
                break;
        }
    }

    private static void dispDamage(int tgt, int dmg) {
        if (tgt == 0) {
            setPanePerDigit(dmg, BattleService.get_Chara1DamagePane());
        } else if (tgt == 1) {
            setPanePerDigit(dmg, BattleService.get_Chara2DamagePane());
        } else if (tgt == 2) {
            setPanePerDigit(dmg, BattleService.get_Chara3DamagePane());
        } else if (tgt == 4) {
            setPanePerDigit(dmg, BattleService.get_MonsterDamagePane());
        }
    }

    private static void setPanePerDigit(int dmg, Pane[] charPane) {
        int digit = 1;
        final int maxDigit = DamageValueCalclator.calcDigit(dmg);

        // 入力Paneはソートされている事が前提
        for (Pane pane : charPane) {
            pane.getChildren().clear();

            if (digit > maxDigit) {
                // 入力パネを全クリアしたいので、breakじゃなくてcontinue
                continue;
            }

            int value = DamageValueCalclator.getDigit(dmg, digit);
            ImageAnimationView loadNumber = PaneAnimationManager.loadNumber(digit, value);
            pane.getChildren().add(loadNumber);
            loadNumber.play();

            digit++;
        }
    }

    public static void dispEffect(ActionEffect effect) {}
}
