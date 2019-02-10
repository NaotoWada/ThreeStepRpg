package application.model.battle.damage.mind;

import java.math.BigDecimal;
import application.model.battle.damage.IDamage;
import dto.chara.abs.Characters;

public class MindDamage implements IDamage {

    private double bias(Characters dest) {
        double b = 1.00 - 1.00 / dest.get_Rank().getVal();
        return new BigDecimal(b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    @Override
    public int calc(Characters src, Characters dest) {
        double damage = (src.get_Intelligence() - (dest.get_Intelligence() * bias(dest)));
        int rnd = (int) (Math.random() * 10) + 1;
        if (damage <= 0) {
            return rnd;
        } else {
            return (int) damage;
        }
    }
}
