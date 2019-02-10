package dto.chara.player;

import java.util.Arrays;
import java.util.Optional;
import application.model.battle.damage.IDamage;
import dto.chara.abs.Characters;
import dto.chara.effect.ActionEffect;
import dto.chara.enums.ACTION;
import dto.chara.enums.EP_TYPE;
import dto.chara.enums.JOBManage.JOB;

public class Priest extends Characters {

    private IDamage _Damage;

    public Priest(String _Name, EP_TYPE _Type, JOB _Job, int _Id, int _Hp, int _MHp, int _Mp,
            int _MMp, int _Strength, int _Intelligence, int _Vitality, int _Speed, int _Accuracy,
            int _Luck, IDamage damage) {
        super._Name = _Name;
        super._Type = _Type;
        super._Job = _Job;
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
    public Priest deepCopy() {
        System.out.println("Priest.deepCopy() プリーストをコピーしました。");
        return new Priest(_Name, _Type, _Job, _Id, _Hp, _MHp, _Mp, _MMp, _Strength, _Intelligence,
                _Vitality, _Speed, _Accuracy, _Luck, _Damage);
    }

    @Override
    public ActionEffect action(Characters[] charas) {
        ActionEffect effect = _Medi.orderBattle(this, charas, _Damage);
        effect.setEffect(EFFECT.WAND);

        return effect;
    }

    private ActionEffect lightBolt(Characters enemy) {
        int damage = (int) (_Intelligence * 2.5 - enemy.get_Vitality());
        enemy.set_Hp(enemy.get_Hp() - damage);
        System.out.println("[" + _Name + "]が[" + enemy.get_Name() + "]を聖なる光で攻撃した。 ダメージ[" + damage
                + "] 攻撃後[" + enemy.get_Hp() + "]");
        this.set_Mp(this.get_Mp() - 20);

        return new ActionEffect(enemy.get_Id(), damage, false, EFFECT.WAND);
    }

    private ActionEffect heal(Characters[] plys) {
        Optional<Characters> injured = detectInjurePlayer(plys);
        if (!injured.isPresent()) {
            return newNothingAction();
        }
        Characters ply = injured.get();
        ply.set_Hp(calcHeal(ply));
        System.out.println("[" + _Name + "]が[" + ply.get_Name() + "]を回復した。 回復量[" + calcHeal(ply)
                + "] 回復後[" + ply.get_Hp() + "]");
        this.set_Mp((_Mp - 500));

        return new ActionEffect(ply.get_Id(), calcHeal(ply), false, EFFECT.HEAL);
    }

    private int calcHeal(Characters ply) {
        int healPoint = (int) (_Intelligence * 1.5);
        if (healPoint + ply.get_Hp() > ply.get_MHp()) {
            return ply.get_MHp(); // 回復後の値がMHPを超えるので全回復
        } else {
            return healPoint + ply.get_Hp(); // MHP未満なので規定ポイントだけ追加した値返却
        }
    }

    private Optional<Characters> detectInjurePlayer(Characters[] plys) {
        return Arrays.stream(plys).filter(s -> s.get_Hp() != s.get_MHp())
                .max((p1, p2) -> (p1.get_MHp() - p1.get_Hp()) - (p2.get_MHp() - p2.get_Hp()));
    }

    public enum SKILL {
        LIGHT_BOLT, //
        HEAL;
    }

    @Override
    public void decideAction() {
        this.set_Act(ACTION.ATTACK);
    }
}
