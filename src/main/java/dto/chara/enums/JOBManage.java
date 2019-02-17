package dto.chara.enums;

import lombok.Getter;

public class JOBManage {

    // ジョブ画像が増えたら増やしていく
    public static final int _JobTypeCapacity = 6;

    public enum GRAVE{

        GRAVE_IMG("墓", "pict/player/mini/grave.png"); //

        @Getter private final String name;
        @Getter private final String miniSizePath;

        GRAVE(String name, String miniSizePath) {

            this.name = name;
            this.miniSizePath = miniSizePath;
        }
    }

    public enum JOB {

        WARRIOR_M("ウォリアー", "pict/player/mini/warrior_m.png", "pict/player/full/warrior_m.png", JOB_PARAM_CORRECTION.WARRIOR_M, JOB_PARAM_STATUS.WARRIOR_M), //
        WARRIOR_F("ウォリアー", "pict/player/mini/warrior_f.png", "pict/player/full/warrior_f.png", JOB_PARAM_CORRECTION.WARRIOR_F, JOB_PARAM_STATUS.WARRIOR_F), //
        WITCH_F  ("ウィッチ",   "pict/player/mini/witch_f.png",   "pict/player/full/witch_f.png",   JOB_PARAM_CORRECTION.WITCH_F,   JOB_PARAM_STATUS.WITCH_F),   //
        PRIEST_F ("プリースト", "pict/player/mini/priest_f.png",  "pict/player/full/priest_f.png",  JOB_PARAM_CORRECTION.PRIEST_F,  JOB_PARAM_STATUS.PRIEST_F),  //
        ARCHER_F ("アーチャー", "pict/player/mini/archer_f.png",  "pict/player/full/archer_f.png",  JOB_PARAM_CORRECTION.ARCHER_F,  JOB_PARAM_STATUS.ARCHER_F),  //
        CHIEF_M  ("シーフ",     "pict/player/mini/chief_m.png",   "pict/player/full/chief_m.png",   JOB_PARAM_CORRECTION.CHIEF_M,   JOB_PARAM_STATUS.CHIEF_M);   //

        @Getter private final String jobName;
        @Getter private final String miniSizePath;
        @Getter private final String fullSizePath;
        @Getter private final JOB_PARAM_CORRECTION param;
        @Getter private final JOB_PARAM_STATUS sts;

        JOB(String jobName, String miniSizePath, String fullSizePath,
            JOB_PARAM_CORRECTION param,
            JOB_PARAM_STATUS sts) {

            this.jobName = jobName;
            this.miniSizePath = miniSizePath;
            this.fullSizePath = fullSizePath;
            this.param = param;
            this.sts = sts;
        }

    }

    public enum JOB_PARAM_CORRECTION {

        //         HP,  MP, STR, INT, VIT, SPD, ACC, LUK の順番で定義する
        WARRIOR_M(1.2, 0.8, 1.6, 0.4, 1.6, 0.8, 1.0, 0.6), //
        WARRIOR_F(1.0, 1.0, 1.4, 0.6, 1.2, 1.2, 1.0, 0.6), //
        WITCH_F  (0.4, 1.6, 0.2, 2.0, 0.4, 1.4, 1.4, 0.6), //
        PRIEST_F (0.8, 1.2, 0.8, 1.6, 1.2, 0.6, 1.0, 0.8), //
        ARCHER_F (1.0, 1.0, 0.8, 0.8, 0.8, 1.2, 1.8, 0.6), //
        CHIEF_M  (0.8, 1.2, 1.0, 0.4, 0.6, 1.8, 0.6, 1.6); //

        @Getter private final double hp;
        @Getter private final double mp;
        @Getter private final double strn;
        @Getter private final double intl;
        @Getter private final double vitl;
        @Getter private final double sped;
        @Getter private final double accr;
        @Getter private final double luck;

        private JOB_PARAM_CORRECTION(double hp,   double mp,
                                     double strn, double intl, double vitl,
                                     double sped, double accr, double luck) {
            this.hp = hp;
            this.mp = mp;
            this.strn = strn;
            this.intl = intl;
            this.vitl = vitl;
            this.sped = sped;
            this.accr = accr;
            this.luck = luck;
        }
    }

    public enum JOB_PARAM_STATUS {

        //        STR, INT, VIT, SPD, ACC, LUK の順番で定義する
        WARRIOR_M(1.0, 0.2, 0.8, 0.4, 0.4, 0.2), //
        WARRIOR_F(0.8, 0.2, 0.8, 0.6, 0.4, 0.2), //
        WITCH_F  (0.2, 1.0, 0.2, 0.8, 0.4, 0.4), //
        PRIEST_F (0.4, 0.8, 0.6, 0.4, 0.4, 0.4), //
        ARCHER_F (0.4, 0.4, 0.4, 0.6, 1.0, 0.2), //
        CHIEF_M  (0.4, 0.2, 0.2, 1.0, 0.4, 0.8); //

        @Getter private final double strn;
        @Getter private final double intl;
        @Getter private final double vitl;
        @Getter private final double sped;
        @Getter private final double accr;
        @Getter private final double luck;

        private JOB_PARAM_STATUS(double strn, double intl, double vitl,
                                 double sped, double accr, double luck) {
            this.strn = strn;
            this.intl = intl;
            this.vitl = vitl;
            this.sped = sped;
            this.accr = accr;
            this.luck = luck;
        }
    }

}
