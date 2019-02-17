package application.model.exp;

import application.model.battle.BattleService;
import application.model.battle.mediator.BattleHelper;
import dto.chara.abs.Characters;
import dto.chara.enums.EnemyEnums.ENEMY;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import lombok.Getter;

/**
 * 経験値をパーティ単位で管理するクラス.
 * <p>
 * 経験値の増加、現在の値の管理を行う.<br>
 * シングルトン設計にしてあり、追加に関してスレッドセーフである<br>
 *
 * @author Naoto Wada
 */
public class ExperienceManager {

    @Getter
    private static final ExperienceManager instance = new ExperienceManager();
    @Getter
    private static long _Experience = 0L;

    /**
     * 経験値を追加し、表示を変更する.
     * <p>
     *
     * @param charas
     */
    public static synchronized void addExp(Characters[] charas) {
        String eName = BattleHelper.detectEnemyIgnoreHp(charas).get_Name();
        long exp = ENEMY.getExp(eName);

        StringProperty prop = BattleService.getInstance().textAreaProperty();
        Platform.runLater(() -> prop.set(textResult(eName, exp, prop)));

        _Experience += exp;
    }

    private static String textResult(String eName, long exp, StringProperty prop) {
        StringBuilder sb = new StringBuilder();
        String exitsTxt = prop.getValue();
        sb.append(exitsTxt);
        sb.append("[");
        sb.append(eName);
        sb.append("]を倒したので、[");
        sb.append(exp);
        sb.append("]の経験値を獲得した。");
        sb.append("\r\n");
        return sb.toString();
    }

}
