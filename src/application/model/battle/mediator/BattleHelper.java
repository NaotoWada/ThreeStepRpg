package application.model.battle.mediator;

import java.util.Arrays;
import dto.chara.abs.Characters;
import dto.chara.enums.EP_TYPE;

public class BattleHelper {

    protected static boolean isDead(Characters src) {
        return src.get_Hp() <= 0;
    }

    protected static int randomTarget(Characters[] players) {
        return (int) ((Math.random()) * players.length);
    }

    public static Characters[] detectPlayer(Characters[] charas) {
        return Arrays.stream(charas).filter(s -> s != null)
                .filter(s -> (s.get_Type() == EP_TYPE.PLAYER)).filter(s -> s.get_Hp() > 0)
                .toArray(s -> new Characters[s]);
    }

    public static Characters[] detectPlayerIgnoreHp(Characters[] charas) {
        return Arrays.stream(charas).filter(s -> s != null)
                .filter(s -> (s.get_Type() == EP_TYPE.PLAYER)).toArray(s -> new Characters[s]);
    }

    public static Characters detectEnemy(Characters[] charas) {
        return Arrays.stream(charas).filter(s -> s != null)
                .filter(s -> (s.get_Type() == EP_TYPE.ENEMY)).filter(s -> s.get_Hp() > 0)
                .findFirst().orElse(null);
    }

    public static Characters detectEnemyIgnoreHp(Characters[] charas) {
        return Arrays.stream(charas).filter(s -> s != null)
                .filter(s -> (s.get_Type() == EP_TYPE.ENEMY)).findFirst().orElse(null);
    }

    protected static String log(String srcName, String destName, int damage, int resultHp) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(srcName);
        sb.append("]が[");
        sb.append(destName);
        sb.append("]を攻撃した ダメージ[");
        sb.append(damage);
        sb.append("] 攻撃後[");
        sb.append(resultHp);
        sb.append("]");

        String resultText = sb.toString();
        System.out.println(resultText);
        return resultText;
    }
}
