package application.model.exp;

import java.util.ResourceBundle;
import application.model.battle.mediator.BattleHelper;
import dto.chara.abs.Characters;
import dto.chara.manage.PartyManager;
import resource.fxml.manage.ResourceBundleUtf8Control;

public class LevelManager {

    private static final ResourceBundle rb =
            ResourceBundle.getBundle("conf/prop/levelTable", new ResourceBundleUtf8Control());

    private static boolean isLevelUp(long exp, int currentLevel) {
        return exp > getNecessaryExp(currentLevel);
    }

    private static long getNecessaryExp(int currentLevel) {
        return Long.valueOf(rb.getString(String.valueOf(currentLevel)));
    }

    public static void levelUp(Characters[] charas) {
        Characters[] alivePlayer = BattleHelper.detectPlayer(charas);
        for (Characters ply : alivePlayer) {
            int id = ply.get_Id();

            // 複数回レベルアップを想定する。将来的には実績で回転させる
            for (int i = 0; i < 100; i++) {
                int level = ply.get_Level();
                long exp = ExperienceManager.getExp(id);
                if (!isLevelUp(exp, level)) {
                    break;
                }

                PartyManager.levelUp(id);
                ExperienceManager.useExp(id, 10);
            }
        }
    }
}
