package dto.chara.manage;

import java.util.Arrays;
import application.model.exp.ExperienceManager;
import consts.PartyConsts;
import dto.chara.abs.Characters;
import lombok.Getter;

public class PartyManager {

    @Getter
    private final static Characters[] _Parties = new Characters[PartyConsts._LimitOfMember];
    @Getter
    private final static Characters[] _Battle = new Characters[PartyConsts._LimitOfMember];

    public static void setParty(Characters memb, int elm) {
        _Parties[elm] = memb;
        ExperienceManager.clearExpAt(elm);
        System.out.println("PartyManager.setParty 格納先[" + elm + "] プレイヤー[" + memb + "]");
    }

    public static void remove(int elm) {
        _Parties[elm] = null;
        ExperienceManager.clearExpAt(elm);
    }

    public static boolean isAlreadyCreate(int elm) {
        return _Parties[elm] != null;
    }

    public static boolean isNothingPlayer() {
        return Arrays.stream(_Parties).allMatch(s -> s == null);
    }

    public static void removeBattle(int id) {
        _Battle[id] = null;
    }

    public static void copyToBattle() {
        clearArr();
        for (Characters player : _Parties) {
            if (player == null) {
                continue;
            }
            _Battle[player.get_Id()] = player.deepCopy();
        }
    }

    private static void clearArr() {
        _Battle[0] = null;
        _Battle[1] = null;
        _Battle[2] = null;
    }

    public static void levelUp(int id) {
        Characters plyParty = _Parties[id];
        Characters plyBattl = _Battle[id];
        upStatus(plyParty);
        upStatus(plyBattl);
    }

    private static void upStatus(Characters ply) {
        System.out.println("レベルアップ前");
        System.out.println(ply);
        ply.set_Level(ply.get_Level() + 1);
        ply.set_MHp((int) (ply.get_MHp() * 1.05 + rndPoint()));
        ply.set_MMp((int) (ply.get_MMp() * 1.05 + rndPoint()));
        ply.set_Strength((int) (ply.get_Strength() * 1.05 + rndPoint()));
        ply.set_Intelligence((int) (ply.get_Intelligence() * 1.05 + rndPoint()));
        ply.set_Vitality((int) (ply.get_Vitality() * 1.05 + rndPoint()));
        ply.set_Speed((int) (ply.get_Speed() * 1.05 + rndPoint()));
        ply.set_Accuracy((int) (ply.get_Accuracy() * 1.05 + rndPoint()));
        ply.set_Luck((int) (ply.get_Luck() * 1.05 + rndPoint()));
        System.out.println(ply);
        System.out.println("レベルアップ後");
    }

    private static int rndPoint() {
        return (int) (Math.random() * 3);
    }
}
