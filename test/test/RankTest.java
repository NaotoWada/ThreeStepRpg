package test;

public class RankTest {

    private static int _Rank = 0;

    public static void main(String[] args) {

        progressRank(1);
        progressRank(9);
        progressRank(10);
        progressRank(11);
        progressRank(19);
        progressRank(20);
        progressRank(21);
    }

    // private static void progressRank(int num) {
    // if ((num / 10) > _Rank && checkDigitDouble(num, 1) == 1) {
    // _Rank++;
    // }
    // System.out.println("num[" + num + "] rank[" + _Rank + "]");
    // }
    private static void progressRank(int num) {
        if (((num - 1) / 10) > _Rank) {
            _Rank++;
        }
        System.out.println("num[" + num + "] rank[" + _Rank + "]");
    }

    public static int checkDigitDouble(int argNum, int digit) {

        int result = 0;
        int dgt = (int) Math.pow(10, digit - 1);
        result = (argNum / dgt) % 10;

        return result;
    }
}
