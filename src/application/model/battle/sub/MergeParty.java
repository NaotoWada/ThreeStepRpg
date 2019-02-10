package application.model.battle.sub;

import java.util.Arrays;
import java.util.Comparator;
import application.model.battle.mediator.BattleHelper;
import dto.chara.abs.Characters;
import dto.chara.manage.EnemyManager;
import dto.chara.manage.PartyManager;

public class MergeParty {

    public static Characters[] loadActionCharas() {
        Characters[] parties = PartyManager.get_Battle();
        Characters[] enemies = EnemyManager.getEnemies();
        return mergePlayerEnemy(parties, enemies);
    }

    protected static Characters[] mergePlayerEnemy(Characters[] parties, Characters[] enemies) {
        int partyExist = countExist(parties);
        int enemyExist = countExist(enemies);
        Characters[] charas = new Characters[partyExist + enemyExist];
        int elm = 0;
        for (Characters player : parties) {
            if (player != null) {
                charas[elm] = player;
                elm++;
            }
        }
        for (Characters enemy : enemies) {
            if (enemy != null) {
                charas[elm] = enemy;
                elm++;
            }
        }
        return sort(charas);
    }

    private static int countExist(Characters[] charas) {
        return (int) Arrays.stream(charas).filter(s -> s != null).count();
    }

    private static Characters[] sort(Characters[] charas) {
        return Arrays.stream(charas).sorted(Comparator.comparing(Characters::get_Speed).reversed())
                .toArray(s -> new Characters[s]);
    }

    public static Characters[] merge(Characters[] charas) {
        Characters[] player = BattleHelper.detectPlayer(charas);
        Characters[] enemies = EnemyManager.getEnemies();
        return mergePlayerEnemy(player, enemies);
    }
}
