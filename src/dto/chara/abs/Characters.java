package dto.chara.abs;

import application.model.battle.mediator.BattleMediator;
import dto.chara.effect.ActionEffect;
import dto.chara.enums.ACTION;
import dto.chara.enums.EP_TYPE;
import dto.chara.enums.EnemyEnums.RANK;
import dto.chara.enums.JOBManage.JOB;
import lombok.Data;

@Data
public abstract class Characters implements IAction, IPlayerCopy {

    protected BattleMediator _Medi = BattleMediator.getInstance();

    protected EP_TYPE _Type;
    protected String _Name;
    protected JOB _Job;
    protected RANK _Rank;
    protected int _Id;
    protected int _Hp;
    protected int _MHp;
    protected int _Mp;
    protected int _MMp;
    protected int _Strength;
    protected int _Intelligence;
    protected int _Vitality;
    protected int _Speed;
    protected int _Accuracy;
    protected int _Luck;
    protected ACTION _Act;

    protected ActionEffect newNothingAction() {
        return new ActionEffect(0, 0, false, EFFECT.NOTHING);
    }
}
