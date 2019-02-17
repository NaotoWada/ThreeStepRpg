package application.model.item.validate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import dto.chara.abs.Characters;
import dto.chara.enums.JOBManage.JOB;
import dto.chara.manage.PartyManager;

public class Validator {

    public static boolean canSelectWarrior() {
        return isMatch(JOB.WARRIOR_F, JOB.WARRIOR_M);
    }

    public static boolean canSelectWitch() {
        return isMatch(JOB.WITCH_F);
    }

    public static boolean canSelectChief() {
        return isMatch(JOB.CHIEF_M);
    }

    public static boolean canSelectPriest() {
        return isMatch(JOB.PRIEST_F);
    }

    private static boolean isMatch(JOB... jobs) {
        List<JOB> playerJobs = Arrays.stream(PartyManager.get_Parties()).filter(Objects::nonNull)
                .map(Characters::get_Job).collect(Collectors.toList());

        for (JOB job : jobs) {
            if (playerJobs.contains(job)) {
                return true;
            }
        }
        return false;
    }
}
