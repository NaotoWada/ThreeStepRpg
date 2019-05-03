package application.model.battle.mediator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import application.model.battle.damage.IDamage;
import application.model.battle.mediator.state.CharaState;
import dto.chara.abs.Characters;
import dto.chara.effect.ActionEffect;
import dto.chara.effect.factory.EffectFactory;
import dto.chara.enums.ACTION;
import dto.chara.enums.EP_TYPE;
import lombok.Getter;

public class BattleMediator implements IBattleMediator {

    private Map<Integer, CharaState> _IdStateMap = new HashMap<>();

    @Getter
    private static final BattleMediator instance = new BattleMediator();
    private static final EffectFactory factory = EffectFactory.getInstance();

    private BattleMediator() {}

    @Override
    public void put(Characters src, CharaState state) {
        Objects.requireNonNull(src);
        Objects.requireNonNull(state);

        _IdStateMap.put(src.get_Id(), state);
    }

    @Override
    public void remove(Characters src) {
        _IdStateMap.remove(src.get_Id());
    }

    @Override
    public ActionEffect orderBattle(Characters src, Characters[] dests, IDamage damage) {
        if (BattleHelper.isDead(src)) {
            return factory.newNothingAction();
        } else {

            return execute(src, dests, damage);
        }
    }


    public ActionEffect execute(Characters src, Characters[] dest, IDamage damage) {

        EP_TYPE type = src.get_Type();
        switch (type) {
            case ENEMY:
                return executeEnemy(src, dest, damage);
            case PLAYER:
                return executePlayer(src, dest, damage);
            default:
                throw new IllegalArgumentException("BattleMediator 入力値不正");
        }
    }

    private ActionEffect executePlayer(Characters src, Characters[] dests, IDamage damage) {
        Characters dest = BattleHelper.detectEnemy(dests);
        if (dest == null) {

            return factory.newNothingAction();
        } else {

            return actionCommand(src, dest, damage);
        }
    }

    private ActionEffect executeEnemy(Characters src, Characters[] dests, IDamage damage) {
        Characters[] destArr = BattleHelper.detectPlayer(dests);
        Characters dest = destArr[BattleHelper.randomTarget(destArr)];

        return actionCommand(src, dest, damage);
    }

    private ActionEffect actionCommand(Characters src, Characters dest, IDamage damage) {

        ACTION act = src.get_Act();
        switch (act) {
            case ATTACK:
                return normalAttack(src, dest, damage);
            case GUAD:
                return factory.newNothingAction();
            case NOTING:
                return factory.newNothingAction();
            case SKILL_ATTACK:
                return factory.newNothingAction();
            case SKILL_HEAL:
                return factory.newNothingAction();
            case RETRY:
                return factory.newNothingAction();
            default:
                break;
        }
        throw new IllegalArgumentException("BattleMediator.calc");
    }

    private ActionEffect normalAttack(Characters src, Characters dest, IDamage damage) {
        int dmg = damage.calc(src, dest);
        dest.set_Hp(dest.get_Hp() - dmg);

        BattleHelper.log(src.get_Name(), dest.get_Name(), dmg, dest.get_Hp());

        return factory.createActionEffect(dest.get_Id(), dmg, false, null);
    }
}
