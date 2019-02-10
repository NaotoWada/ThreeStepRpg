package dto.chara.factory;

import application.model.battle.damage.physical.PhysicalMobDamage;
import dto.chara.abs.Characters;
import dto.chara.enemy.boss.Dragon;
import dto.chara.enemy.high.GoblinMaster;
import dto.chara.enemy.low.Bat;
import dto.chara.enemy.low.Egg;
import dto.chara.enemy.low.Mouse;
import dto.chara.enemy.low.Slime;
import dto.chara.enemy.mid.Goblin;
import dto.chara.enums.EP_TYPE;
import dto.chara.enums.EnemyEnums.ENEMY;
import dto.chara.enums.EnemyEnums.ENEMY_PARAM;

public class EnemyFactory {

    public Characters newEnemy(ENEMY enemy) {
        return create(select(enemy), enemy);
    }

    private Characters select(ENEMY enemy) {
        switch (enemy) {
            case TIRANT:         return new Dragon(new PhysicalMobDamage());
            case GOBLIN:         return new Goblin(new PhysicalMobDamage());
            case BAT:            return new Bat(new PhysicalMobDamage());
            case EGG:             return new Egg(new PhysicalMobDamage());
            case GOBLIN_MASTER: return new GoblinMaster(new PhysicalMobDamage());
            case MOUSE:          return new Mouse(new PhysicalMobDamage());
            case SLIME:         return new Slime(new PhysicalMobDamage());
            default:
                throw new IllegalArgumentException("EnemyFactory select 入力値不正");
        }
    }

    public Characters create(Characters chara, ENEMY enemy) {
        ENEMY_PARAM param = enemy.getParam();
        chara.set_Name(enemy.getName());
        chara.set_Type(EP_TYPE.ENEMY);
        chara.set_Id(4);
        chara.set_Rank(enemy.getRank());

        chara.set_MHp(param.getHp());
        chara.set_MMp(param.getMp());
        chara.set_Hp(param.getHp());
        chara.set_Mp(param.getMp());

        chara.set_Strength(param.getStrn());
        chara.set_Intelligence(param.getIntl());
        chara.set_Vitality(param.getVitl());
        chara.set_Speed(param.getSped());
        chara.set_Accuracy(param.getAccr());
        chara.set_Luck(param.getLuck());
        return chara;
    }
}
