package application.model.money;

import application.model.battle.BattleService;
import application.model.battle.mediator.BattleHelper;
import dto.chara.abs.Characters;
import dto.chara.enums.EnemyEnums.ENEMY;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import lombok.Getter;

/**
 * 所持金をパーティ単位で管理するクラス.
 * <p>
 * 所持金の増加、現在の値の管理を行う.<br>
 * シングルトン設計にしてあり、追加に関してスレッドセーフである<br>
 *
 * @author Naoto Wada
 */
public class MoneyManager {

    @Getter
    private static final MoneyManager instance = new MoneyManager();
    @Getter
    private static long _Money = 0L;

    public static synchronized void addMoney(Characters[] charas) {
        String eName = BattleHelper.detectEnemyIgnoreHp(charas).get_Name();
        long earn = ENEMY.getMoney(eName);

        StringProperty prop = BattleService.getInstance().textAreaProperty();
        Platform.runLater(() -> prop.set(textResult(eName, earn, prop)));

        _Money += earn;
    }

    private static String textResult(String eName, long earn, StringProperty prop) {
        StringBuilder sb = new StringBuilder();
        String exitsTxt = prop.getValue();
        sb.append(exitsTxt);
        sb.append("[");
        sb.append(earn);
        sb.append("]のお金を獲得した。");
        sb.append("\r\n");
        return sb.toString();
    }

}
