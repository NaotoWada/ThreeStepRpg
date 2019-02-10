package dto.chara.effect.factory;

import dto.chara.abs.IAction.EFFECT;
import dto.chara.effect.ActionEffect;
import lombok.Getter;

public class EffectFactory {

    @Getter
    private static final EffectFactory instance = new EffectFactory();

    private EffectFactory() {}

    public ActionEffect newNothingAction() {
        return new ActionEffect(0, 0, false, EFFECT.NOTHING);
    }

    public ActionEffect createActionEffect(int id, int dmg, boolean b, EFFECT object) {
        return new ActionEffect(id, dmg, b, object);
    }
}
