package dto.chara.enums;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

/**
 * 戦闘時の行動を列挙する.
 * <p>
 * なるべく具象化しない形で定義して、具体的な処理は各実装側で記述する.<br>
 * 確率の閾値は{@code 5%-100%}で必ず5%刻み.重複する確率は認める.<br>
 *
 * @author Naoto Wada
 */
@Getter
public enum ACTION {

    ATTACK(75), //
    SKILL_ATTACK(15), //
    SKILL_HEAL(90), //
    GUAD(5), //
    NOTING(5), //
    RETRY(5);// リトライは5%を埋め合わせるために使用する。選択された場合は再度ランダム取得する

    private final int prcnt;
    @Getter
    private final static List<ACTION> lowEnemyList;
    @Getter
    private final static List<ACTION> atkList;
    @Getter
    private final static List<ACTION> healList;
    static {
        lowEnemyList = Arrays.asList(ATTACK, GUAD, NOTING, NOTING, NOTING, RETRY);

        atkList = Arrays.asList(ATTACK, SKILL_ATTACK, GUAD, RETRY);
        healList = Arrays.asList(SKILL_HEAL, GUAD, RETRY);
    }


    ACTION(int prcnt) {
        this.prcnt = prcnt;
    }

    /**
     * 入力のリストから行動を確立ランダム取得する.
     * <p>
     * 入力リストを昇順ソート後、20分割した配列に5%ずつの閾値を詰める.<br>
     * ランダム関数で得た値を作成した配列から直接取り出す<br>
     *
     * @param actset
     * @return ランダム選択したアクションENUM
     */
    public static ACTION getRnd(List<ACTION> acts) {
        List<ACTION> sort = sort(acts);
        ACTION[] dicTbl = makeTbl(sort);
        return selectRecursive(dicTbl);
    }

    private static ACTION selectRecursive(ACTION[] dicTbl) {
        int rnd = random();
        for (int i = rnd; i < 20; i++) {
            ACTION select = dicTbl[i];
            if (select == null) {
                continue;
            }

            if (select == ACTION.RETRY) {
                selectRecursive(dicTbl);
            } else {
                return select;
            }
        }
        throw new IllegalArgumentException("該当するアクションが取得できませんでした");
    }

    private static ACTION[] makeTbl(List<ACTION> sort) {
        ACTION[] ret = new ACTION[20];
        int elm = 0;
        for (ACTION act : sort) {
            elm += act.getPrcnt() / 5;
            ret[elm - 1] = act;
        }
        return ret;
    }

    private static List<ACTION> sort(List<ACTION> acts) {
        return acts.stream().sorted(Comparator.comparing(ACTION::getPrcnt))
                .collect(Collectors.toList());
    }

    private static int random() {
        return (int) (Math.random() * 20);
    }
}
