package app;

import javafx.scene.layout.Pane;

public class Looptest {

    private static final int _MaxDigitDamage = 9;

    protected static Pane[] _Chara1DamagePane = new Pane[_MaxDigitDamage];
    protected static Pane[] _Chara2DamagePane = new Pane[_MaxDigitDamage];
    protected static Pane[] _Chara3DamagePane = new Pane[_MaxDigitDamage];

    public static void main(String[] args) {
        Pane[] panes = new Pane[27];
        allocateNumPane(panes);
    }


    private static void allocateNumPane(Pane... panes) {
        // ソートしてある事を前提に使用する事
        int firstCharaLimit = 9;
        int secondCharaLimit = 18;

        // 1キャラ目
        for (int i = 0; i < _MaxDigitDamage; i++) {
            _Chara1DamagePane[i] = panes[i];
            System.out.println("1キャラ目[" + i + "]");
        }

        // 2キャラ目
        for (int i = 0; i < _MaxDigitDamage; i++) {
            int paneSecond = i + firstCharaLimit;
            _Chara2DamagePane[i] = panes[paneSecond];
            System.out.println("2キャラ目[" + i + "] パネ[" + paneSecond + "]");
        }

        // 3キャラ目
        for (int i = 0; i < _MaxDigitDamage; i++) {
            int paneThird = i + secondCharaLimit;
            _Chara3DamagePane[i] = panes[paneThird];
            System.out.println("3キャラ目[" + i + "] パネ[" + paneThird + "]");
        }
    }
}
