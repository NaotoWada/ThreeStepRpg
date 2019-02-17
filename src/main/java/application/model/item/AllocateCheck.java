package application.model.item;

import application.model.item.validate.Validator;
import dto.chara.enums.JOBManage.JOB;

public class AllocateCheck {

    public static void execute(JOB job) {
        switch (job) {
            case ARCHER_F:
                break;
            case CHIEF_M:
                break;
            case PRIEST_F:
                break;
            case WARRIOR_F:
            case WARRIOR_M:
                if (Validator.canSelectWarrior()) {

                    System.out.println("OKっす");
                } else {
                    System.out.println("むりっす");
                }
                break;
            case WITCH_F:
                break;
            default:
                break;

        }
    }

}
