package application.model.battle.damage.physical;

import java.math.BigDecimal;
import application.model.battle.damage.IDamage;
import dto.chara.abs.Characters;

public class PhysicalDamage implements IDamage {

    private double bias(Characters dest) {
        double b = 1.00 - 1.00 / dest.get_Rank().getVal();
        return new BigDecimal(b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    @Override
    public int calc(Characters src, Characters dest) {
        double damage = (src.get_Strength() - (dest.get_Vitality() * bias(dest)));
        int rnd = (int) (Math.random() * 10) + 1;
        if (damage <= 0) {
            return rnd;
        } else {
            return (int) damage;
        }
    }
}
