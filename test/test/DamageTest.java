package test;

import java.math.BigDecimal;

public class DamageTest {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int dmg = (int) (Math.random() * 100) + 1;
            if (dmg == 0) {
                System.out.println("0");
                break;
            }
        }
        exec();
    }

    private static void exec() {
        int atk = 100;
        int def = 30;

        // 攻撃力－A×防御力
        // A＝(1-1/N)

        int weak = 3;
        int midd = 10;
        int larg = 20;

        calcDamage(atk, def, bias(weak));
        calcDamage(atk, def, bias(midd));
        calcDamage(atk, def, bias(larg));

        atk = 100 * 33333333;
        int abs = Math.abs(atk);
        def = 100 * 33333333;
        int defA = Math.abs(def);
        System.out.println("攻撃力変更:" + abs);
        calcDamage(abs, defA, bias(larg));
    }

    private static double bias(int bias) {
        double t = 1.00 / bias;
        double b = 1.00 - t;
        return new BigDecimal(b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        // System.out.printf("暫定[%s], 倍率[%s], バイアス[%s]\r\n", t, bias, b);
    }

    private static double calcDamage(int atk, int def, double bias) {
        double damage = (atk - (def * bias));
        System.out.printf("攻撃力[%s] - 防御力[%s] : ダメージ[%s]\r\n", atk, def, damage);
        return damage;
    }

}
