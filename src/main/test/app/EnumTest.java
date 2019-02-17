package app;

import dto.chara.enums.EP_TYPE;

public class EnumTest {

    public static void main(String[] args) {

        EP_TYPE ene = EP_TYPE.ENEMY;
        EP_TYPE ply = EP_TYPE.PLAYER;
        System.out.println(ene == ply);
        // System.out.println(ply == ply);
        // System.out.println(ene == ene);
    }
}
