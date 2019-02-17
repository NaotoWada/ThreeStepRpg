package dto.chara.enemy.boss;

import application.model.battle.damage.IDamage;
import dto.chara.abs.Characters;
import dto.chara.effect.ActionEffect;
import dto.chara.enums.ACTION;

public class Dragon extends Characters {

    private IDamage _Damage;

    public Dragon(IDamage damage) {
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
        super.set_Act(ACTION.ATTACK);
    }

    @Override
    public Characters deepCopy() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }
}
