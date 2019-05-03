package dto.chara.player;

import application.model.battle.damage.IDamage;
import dto.chara.abs.Characters;
import dto.chara.effect.ActionEffect;
import dto.chara.enums.ACTION;
import dto.chara.enums.EP_TYPE;
import dto.chara.enums.JOBManage.JOB;

public class Witch extends Characters {

    private IDamage _Damage;

    public Witch(String _Name, EP_TYPE _Type, JOB _Job, int _Level, int _Id, int _Hp, int _MHp,
            int _Mp, int _MMp, int _Strength, int _Intelligence, int _Vitality, int _Speed,
            int _Accuracy, int _Luck, IDamage damage) {
        super._Name = _Name;
        super._Type = _Type;
        super._Job = _Job;
        super._Level = _Level;
        super._Id = _Id;
        super._Hp = _Hp;
        super._MHp = _MHp;
        super._Mp = _Mp;
        super._MMp = _MMp;
        super._Strength = _Strength;
        super._Intelligence = _Intelligence;
        super._Vitality = _Vitality;
        super._Speed = _Speed;
        super._Accuracy = _Accuracy;
        super._Luck = _Luck;
        this._Damage = damage;
    }

    @Override
    public ActionEffect action(Characters[] charas) {
        ActionEffect effect = _Medi.orderBattle(this, charas, _Damage);
        effect.setEffect(EFFECT.WAND);

        return effect;
    }

    @Override
    public Witch deepCopy() {
        System.out.println("Witch.deepCopy() プリーストをコピーしました。");
        return new Witch(_Name, _Type, _Job, _Level, _Id, _Hp, _MHp, _Mp, _MMp, _Strength,
                _Intelligence, _Vitality, _Speed, _Accuracy, _Luck, _Damage);
    }

    @Override
    public void decideAction() {
        this.set_Act(ACTION.ATTACK);
    }
}
