package application.model.exp;

import java.util.HashMap;
import java.util.Map;
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
    private static Map<Integer, Long> expMap = new HashMap<>();

    /**
     * 経験値を追加し、表示を変更する.
     * <p>
     *
     * @param charas
     */
    public static synchronized void addExp(Characters[] charas) {
        String eName = BattleHelper.detectEnemyIgnoreHp(charas).get_Name();
        long earn = ENEMY.getExp(eName);

        StringProperty prop = BattleService.getInstance().textAreaProperty();
        Platform.runLater(() -> prop.set(textResult(eName, earn, prop)));

        // 生存している全てのキャラクターに経験値を加算する
        Characters[] alivePlayer = BattleHelper.detectPlayer(charas);
        for (Characters ply : alivePlayer) {
            int id = ply.get_Id();
            Long current = expMap.get(id);
            expMap.put(id, current + earn);
        }
    }

    public static void clearExpAt(int id) {
        expMap.put(id, 0L);
    }

    public static long getExp(int id) {
        return expMap.get(id);
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

    public static void useExp(int id, int used) {
        Long nowExp = expMap.get(id);
        expMap.put(id, nowExp - used);
    }

}
