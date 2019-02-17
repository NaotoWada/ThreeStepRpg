package application.model.battle.wave;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import dto.chara.enums.EnemyEnums.ENEMY;

public class MobStorage {

    private static final Map<Integer, List<ENEMY>> _MobMapNormal;
    private static final Map<Integer, ENEMY> _MobMapMidBoss;
    private static final Map<Integer, ENEMY> _MobMapBoss;

    static {
        _MobMapNormal = new HashMap<>();
        _MobMapNormal.put(0, Arrays.asList(ENEMY.SLIME, ENEMY.BAT, ENEMY.EGG, ENEMY.MOUSE));
    }
    static {
        _MobMapMidBoss = new HashMap<>();
        _MobMapMidBoss.put(0, ENEMY.GOBLIN);
    }
    static {
        _MobMapBoss = new HashMap<>();
        _MobMapBoss.put(0, ENEMY.GOBLIN_MASTER);
    }

    public static ENEMY selectRndmEnemy(int wave) {
        List<ENEMY> enemies = _MobMapNormal.get(wave);
        return random(enemies);
    }

    private static ENEMY random(List<ENEMY> enes) {
        int rnd = (int) (Math.random() * enes.size());
        return enes.get(rnd);
    }

    public static ENEMY selectMidBoss(int wave) {
        return _MobMapMidBoss.get(wave);
    }

    public static ENEMY selectBoss(int wave) {
        return _MobMapBoss.get(wave);
    }
}
