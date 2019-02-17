package dto.chara.abs;

import dto.chara.effect.ActionEffect;

public interface IAction {

    public void decideAction();

    public ActionEffect action(Characters[] charas);

    public enum EFFECT {
        SWORD, //
        KNIFE, //
        BOW, //
        WAND, //
        ARROW, //
        NAIL, //
        HEAL, //
        NOTHING,//
    }
}
