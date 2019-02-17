package dto.chara.manage;

import consts.PartyConsts;
import dto.chara.abs.Characters;
import dto.chara.enums.EnemyEnums.ENEMY;
import dto.chara.factory.EnemyFactory;

public class EnemyManager {

    private final static EnemyFactory factory = new EnemyFactory();
    private final static Characters[] _Enemies = new Characters[PartyConsts._LimitOfMember];

    public static void setEnemy(ENEMY enemy, int elm) {
        _Enemies[elm] = factory.newEnemy(enemy);
        System.out.println("EnemyManager.setEnemy 格納先[" + elm + "] 敵[" + enemy + "]");
    }

    public static Characters[] getEnemies() {
        return _Enemies;
    }
}
