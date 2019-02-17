package application.model.charamenu.sts;

import application.model.charamenu.enums.COMMAND;
import lombok.Data;
import resource.audio.SoundEffectController.SE;

public class StatusChanger {

    public ChangeResult change(COMMAND cmd, int sts, int remain) {

        ChangeResult result = new ChangeResult();
        result.setStsNum(sts);
        result.setRemainPoint(remain);

        switch (cmd) {
            case INCREASE:

                if (remain == 0) {
                    SE.CANCEL.play();
                    result.setChangeWord("ポイントが余ってません");
                    return result;
                }

                editResultIncreate(result, sts, remain);
                break;

            case DECREASE:
                editResultDecrease(result, sts, remain);
                break;
        }
        return result;
    }

    private void editResultDecrease(ChangeResult result, int sts, int remain) {
        if (sts == 1) {
            SE.CANCEL.play();
            result.setChangeWord("これ以上は低くできない");
            return;
        }
        SE.CURSOR.play();
        result.setStsNum(--sts);
        result.setRemainPoint(++remain);
    }

    private void editResultIncreate(ChangeResult result, int sts, int remain) {
        if (sts == 5) {
            SE.CANCEL.play();
            result.setChangeWord("これ以上は高くできません");
            return;
        }
        SE.CURSOR.play();
        result.setStsNum(++sts);
        result.setRemainPoint(--remain);
    }

    @Data
    public class ChangeResult {
        private String changeWord;
        private int remainPoint;
        private int stsNum;
    }

}
