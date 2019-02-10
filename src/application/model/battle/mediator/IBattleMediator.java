package application.model.battle.mediator;

import application.model.battle.damage.IDamage;
import application.model.battle.mediator.state.CharaState;
import dto.chara.abs.Characters;
import dto.chara.effect.ActionEffect;

public interface IBattleMediator {

    public ActionEffect orderBattle(Characters src, Characters[] dests, IDamage damage);

    public void put(Characters src, CharaState state);

    public void remove(Characters src);
}
