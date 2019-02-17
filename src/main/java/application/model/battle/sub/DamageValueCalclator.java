package application.model.battle.sub;

public class DamageValueCalclator {

    public static int calcDigit(int num) {
        // double値で計算された後、1を加算しintにキャストする事で小数点を無視する.
        return (int) (Math.log10(num) + 1);
    }

    public static int getDigit(int dmg, int digit) {
        int dgt = (int) Math.pow(10, digit - 1);
        return (dmg / dgt) % 10;
    }
}
