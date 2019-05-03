package dto.chara.enums;

import lombok.Getter;

public class EnemyEnums {

    @Getter
    public enum ENEMY {

        SLIME("スライム", "pict/enemy/slime.png", ENEMY_PARAM.SLIME, RANK.LOW), //
        BAT("コウモリ", "pict/enemy/bat.png", ENEMY_PARAM.BAT, RANK.LOW), //
        MOUSE("ネズミ", "pict/enemy/mouse.png", ENEMY_PARAM.MOUSE, RANK.LOW), //
        EGG("ミスティックエッグ", "pict/enemy/egg.png", ENEMY_PARAM.EGG, RANK.LOW), //
        GOBLIN("ゴブリン", "pict/enemy/goblin.png", ENEMY_PARAM.GOBLIN, RANK.MID), //
        GOBLIN_MASTER("ゴブリンマスター", "pict/enemy/goblinMaster.png", ENEMY_PARAM.GOBLIN_MASTER,
                RANK.HIGH), //
        SCRATCHER("スクラッチャー", "pict/enemy/scratcher.png", ENEMY_PARAM.SCRATCHER, RANK.LOW), //
        LIZARD("リザード", "pict/enemy/lizard.png", ENEMY_PARAM.LIZARD, RANK.LOW), //
        LION("ライオン", "pict/enemy/lion.png", ENEMY_PARAM.LION, RANK.LOW), //
        GRIZZLY("グリズリー", "pict/enemy/grizzly.png", ENEMY_PARAM.GRIZZLY, RANK.LOW), //
        ELEHORN("エレホーン", "pict/enemy/elehorn.png", ENEMY_PARAM.ELEHORN, RANK.LOW), //
        BEOWULF("ベオウルフ", "pict/enemy/beowulf.png", ENEMY_PARAM.BEOWULF, RANK.LOW), //
        RINO("ライノ", "pict/enemy/rino.png", ENEMY_PARAM.RINO, RANK.MID), //
        BELG("ベルグ", "pict/enemy/belg.png", ENEMY_PARAM.BELG, RANK.HIGH), //

        TIRANT("ドラゴン", "pict/enemy/dragon.png", ENEMY_PARAM.TYRANT, RANK.BOSS); //

        private final String name;
        private final String path;
        private final ENEMY_PARAM param;
        private final RANK rank;

        private ENEMY(String name, String path, ENEMY_PARAM param, RANK rank) {
            this.name = name;
            this.path = path;
            this.param = param;
            this.rank = rank;
        }

        /**
         * モンスター名から経験値を取得する.
         * <p>
         * nullや空行を入力した場合は{@code 0}を返却する.<br>
         * 該当がない場合も同様に{@code 0}を返却する.<br>
         *
         * @param name モンスター名
         * @return 経験値
         */
        public static long getExp(String name) {
            if (name == null || name.equals("")) {
                return 0;
            }
            for (ENEMY enemy : ENEMY.values()) {
                if (enemy.getName().equals(name)) {
                    return enemy.getParam().getExper();
                }
            }
            return 0;
        }

        /**
         * モンスター名からお金を取得する.
         * <p>
         * nullや空行を入力した場合は{@code 0}を返却する.<br>
         * 該当がない場合も同様に{@code 0}を返却する.<br>
         *
         * @param name モンスター名
         * @return お金
         */
        public static long getMoney(String name) {
            if (name == null || name.equals("")) {
                return 0;
            }
            for (ENEMY enemy : ENEMY.values()) {
                if (enemy.getName().equals(name)) {
                    return enemy.getParam().getMoney();
                }
            }
            return 0;
        }
    }

    /**
     * モンスターの定義を列挙する.
     * <p>
     * 最小値は1で、最大値は{@code Integer.MaxInt}以下とする.
     *
     * @author Naoto Wada
     *
     */
    @Getter
    public enum ENEMY_PARAM {

        // HP, MP, STR, INT, VIT, SPD, ACC, LUK MONEY EXP の順番で定義する
        SLIME(230, 10, 100, 30, 45, 10, 20, 5, 5, 1), //
        BAT(200, 10, 140, 5, 30, 70, 20, 5, 7, 2), //
        MOUSE(220, 10, 120, 50, 50, 60, 20, 5, 4, 2), //
        EGG(350, 10, 10, 75, 70, 5, 100, 5, 0, 15), //
        GOBLIN(550, 10, 170, 65, 65, 70, 100, 10, 50, 25), //
        GOBLIN_MASTER(1200, 100, 250, 100, 120, 90, 100, 15, 500, 200), //
        SCRATCHER(400, 50, 170, 80, 20, 120, 120, 15, 25, 25), //
        LIZARD(700, 30, 200, 30, 80, 90, 50, 5, 35, 20), //
        LION(1200, 150, 200, 200, 120, 80, 100, 10, 25, 55), //
        GRIZZLY(800, 120, 400, 10, 10, 10, 5, 5, 10, 60), //
        ELEHORN(1500, 90, 220, 200, 400, 80, 250, 5, 150, 50), //
        BEOWULF(600, 100, 190, 90, 100, 180, 100, 5, 100, 70), //
        RINO(1800, 100, 120, 200, 600, 100, 200, 10, 700, 520), //
        BELG(3000, 100, 500, 400, 400, 150, 100, 25, 2000, 1655), //
        TYRANT(1250, 500, 200, 100, 10, 60, 010, 010, 25000000, 100); //

        private final int hp;
        private final int mp;
        private final int strn;
        private final int intl;
        private final int vitl;
        private final int sped;
        private final int accr;
        private final int luck;
        private final int money;
        private final int exper;

        private ENEMY_PARAM(int hp, int mp, int strn, int intl, int vitl, int sped, int accr,
                int luck, int money, int exper) {
            this.hp = hp;
            this.mp = mp;
            this.strn = strn;
            this.intl = intl;
            this.vitl = vitl;
            this.sped = sped;
            this.accr = accr;
            this.luck = luck;
            this.money = money;
            this.exper = exper;
        }

    }

    @Getter
    public enum RANK {
        LOW(3), //
        MID(5), //
        HIGH(10), //
        BOSS(20);
        private final int val;

        private RANK(int val) {
            this.val = val;
        }
    }
}

