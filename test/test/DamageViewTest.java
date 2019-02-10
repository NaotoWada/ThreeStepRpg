package test;

public class DamageViewTest {

    public static void main(String[] args) {

        // int[] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999,
        // Integer.MAX_VALUE };


        checkDigitDouble(987, 4);
    }

    public static int checkDigitDouble(int argNum, int digit) {

        int result = 0;
        int dgt = (int) Math.pow(10, digit - 1);
        result = (argNum / dgt) % 10;

        System.out.println("[" + digit + "]桁目は[" + dgt + "]で割って10の余りの結果[" + result + "]");
        return result;
    }
}
