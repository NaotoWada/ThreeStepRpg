package application.model.battle.damage;

import dto.chara.abs.Characters;

public interface IDamage {

    public int calc(Characters src, Characters dest);
}
