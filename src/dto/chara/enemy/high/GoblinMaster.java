package dto.chara.enemy.high;

import application.model.battle.damage.IDamage;
import dto.chara.abs.Characters;
import dto.chara.effect.ActionEffect;
import dto.chara.enums.ACTION;

public class GoblinMaster extends Characters {

    private IDamage _Damage;

    public GoblinMaster(IDamage damage) {
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
        ACTION rnd = ACTION.getRnd(ACTION.getAtkList());
        super.set_Act(rnd);
    }

    @Override
    public Characters deepCopy() {
        return null;
    }
}
