package application.model.battle;

import application.model.battle.mediator.BattleHelper;
import dto.chara.abs.Characters;

public class ProgressBarHelper {

    public static void update(STS sts, Characters[] charas) {
        Characters[] players = BattleHelper.detectPlayerIgnoreHp(charas);
        for (Characters ply : players) {
            updateProgress(sts, ply);
        }

        Characters enemy = BattleHelper.detectEnemyIgnoreHp(charas);
        BattleService.get_HpBarMonster().setProgress(calcHp(enemy));
    }

    private static void updateProgress(STS sts, Characters ply) {
        switch (sts) {
            case HP:
                updateHp(ply);
                break;
            case MP:
                updateMp(ply);
                break;
        }
    }

    private static void updateHp(Characters ply) {
        int id = ply.get_Id();
        if (id == 0) {
            BattleService.get_HpBarChara1().setProgress(calcHp(ply));
        } else if (id == 1) {
            BattleService.get_HpBarChara2().setProgress(calcHp(ply));
        } else if (id == 2) {
            BattleService.get_HpBarChara3().setProgress(calcHp(ply));
        }
    }

    private static double calcHp(Characters chr) {
        return ((double) chr.get_Hp()) / ((double) chr.get_MHp());
    }

    private static void updateMp(Characters ply) {
        int id = ply.get_Id();
        if (id == 0) {
            BattleService.get_MpBarChara1().setProgress(calcMp(ply));
        } else if (id == 1) {
            BattleService.get_MpBarChara2().setProgress(calcMp(ply));
        } else if (id == 2) {
            BattleService.get_MpBarChara3().setProgress(calcMp(ply));
        }
    }

    private static double calcMp(Characters chr) {
        return ((double) chr.get_Mp()) / ((double) chr.get_MMp());
    }

    public enum STS {
        HP, MP;
    }
}
