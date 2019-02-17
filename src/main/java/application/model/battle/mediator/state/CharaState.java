package application.model.battle.mediator.state;

import java.util.HashMap;
import java.util.Map;
import dto.chara.enums.ACTION;
import lombok.Getter;

@Getter
public class CharaState {

    private int id;
    private ACTION act;
    private boolean isDead;
    private Map<String, Integer> validTurnMap = new HashMap<>();
}
