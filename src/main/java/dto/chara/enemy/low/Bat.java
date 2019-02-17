package dto.chara.enemy.low;

import application.model.battle.damage.IDamage;
import dto.chara.abs.Characters;
import dto.chara.effect.ActionEffect;
import dto.chara.enums.ACTION;

public class Bat extends Characters {

    private IDamage _Damage;

    public Bat(IDamage damage) {
        _Damage = damage;
    }

    @Override
    public ActionEffect action(Characters[] charas) {

        ActionEffect effect = _Medi.orderBattle(this, charas, _Damage);
        effect.setEffect(EFFECT.NAIL);

        return effect;
    }

    @Override
    public void decideAction() {
        ACTION rnd = ACTION.getRnd(ACTION.getLowEnemyList());
        super.set_Act(rnd);
    }

    @Override
    public Characters deepCopy() {
        return null;
    }
}
