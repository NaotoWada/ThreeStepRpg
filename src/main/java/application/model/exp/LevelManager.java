package application.model.exp;

import application.model.battle.mediator.BattleHelper;
import dto.chara.abs.Characters;
import dto.chara.manage.PartyManager;

public class LevelManager {

    private static boolean isLevelUp(long exp, int targetLevel) {
        return exp > 10;
    }

    public static void levelUp(Characters[] charas) {
        Characters[] alivePlayer = BattleHelper.detectPlayer(charas);
        for (Characters ply : alivePlayer) {
            int id = ply.get_Id();

            // 複数回レベルアップを想定する。将来的には実績で回転させる
            for (int i = 0; i < 100; i++) {
                int level = ply.get_Level();
                long exp = ExperienceManager.getExp(id);
                if (!isLevelUp(exp, level + 1)) {
                    break;
                }

                PartyManager.levelUp(id);
                ExperienceManager.useExp(id, 10);
            }
        }
    }


}
