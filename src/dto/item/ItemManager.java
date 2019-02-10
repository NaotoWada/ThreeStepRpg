package dto.item;

import java.util.HashSet;
import java.util.Set;
import dto.item.enums.ITEM;

public class ItemManager {

    public static final int _ItemCapacity = 20;
    private static boolean[] _Unlocks = new boolean[_ItemCapacity];

    public static Set<Integer> getUnlocks() {
        Set<Integer> unlocks = new HashSet<>();
        for (int i = 0; i < _ItemCapacity; i++) {
            if (_Unlocks[i]) {
                unlocks.add(i);
            }
        }
        return unlocks;
    }

    public static void unlock(ITEM... items) {
        for (ITEM item : items) {
            _Unlocks[item.ordinal()] = true;
        }
    }

}
