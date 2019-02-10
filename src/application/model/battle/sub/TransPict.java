package application.model.battle.sub;

import java.util.Arrays;
import application.model.battle.BattleService;
import dto.chara.abs.Characters;
import dto.chara.enums.EP_TYPE;
import javafx.application.Platform;
import resource.img.PaneAnimationManager;

public class TransPict {

    public static void toGrave(Characters[] charas) {
        Characters[] deathPlayer = Arrays.stream(charas).filter(s -> s.get_Hp() <= 0)
                .filter(s -> s.get_Type() == EP_TYPE.PLAYER).toArray(s -> new Characters[s]);
        for (Characters ply : deathPlayer) {
            death(ply);
        }
    }

    private static void death(Characters chr) {
        Platform.runLater(() -> changePicutreGrave(chr));
    }

    private static void changePicutreGrave(Characters chr) {
        PaneAnimationManager.convertGrave(BattleService.get_CharaBattlePanes(), chr.get_Id());
    }
}
