package app;

import dto.chara.enums.ACTION;

public class SkillRandomTableTest {

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            ACTION rnd = ACTION.getRnd(ACTION.getLowEnemyList());
            System.out.println(rnd);
        }
    }
}
