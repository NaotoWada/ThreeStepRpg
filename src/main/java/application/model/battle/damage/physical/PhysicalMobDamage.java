package application.model.battle.damage.physical;

import application.model.battle.damage.IDamage;
import dto.chara.abs.Characters;

public class PhysicalMobDamage implements IDamage {

    @Override
    public int calc(Characters src, Characters dest) {
        double damage = (src.get_Strength() - (dest.get_Vitality() * 0.8));
        int rnd = (int) (Math.random() * 10) + 1;
        if (damage <= 0) {
            return rnd;
        } else {
            return (int) damage;
        }
    }
}
