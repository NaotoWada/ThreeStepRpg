package dto.chara.effect;

import dto.chara.abs.IAction.EFFECT;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ActionEffect {

    private int target;
    private int effectValue;
    private boolean isAllPlayer;
    private EFFECT effect;

    public ActionEffect() {};
}
