package dto.chara.manage;

import java.util.Arrays;
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
        System.out.println("PartyManager.setParty 格納先[" + elm + "] プレイヤー[" + memb + "]");
    }

    public static void remove(int elm) {
        _Parties[elm] = null;
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
}
