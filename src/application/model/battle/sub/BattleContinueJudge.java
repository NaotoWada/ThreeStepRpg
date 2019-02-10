package application.model.battle.sub;

import java.util.Arrays;
import application.model.battle.mediator.BattleHelper;
import dto.chara.abs.Characters;

public class BattleContinueJudge {

    public static boolean isPlayerAllDead(Characters[] charas) {
        return checkDeath(BattleHelper.detectPlayerIgnoreHp(charas));
    }

    public static boolean canNextWave(Characters[] charas) {
        Characters enemy = BattleHelper.detectEnemyIgnoreHp(charas);
        return enemy.get_Hp() <= 0;
    }

    private static boolean checkDeath(Characters[] charas) {
        return Arrays.stream(charas).allMatch(s -> s.get_Hp() <= 0);
    }
}
