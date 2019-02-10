package dto.achieve;

import dto.achieve.enums.EVENT;
import dto.item.ItemManager;
import dto.item.enums.ITEM;

public class AchieveManager {

    public static void achieve(EVENT event) {

        switch (event) {
            case FIRST_OUTSIDE:
                ItemManager.unlock(ITEM.MEDHICAL, ITEM.PUCHIRING, ITEM.FLAG);
                break;
            case FIRST_KILL:
                ItemManager.unlock(ITEM.BOOK, ITEM.BOOTS, ITEM.SHIELD, ITEM.BILL);
                break;
            case STAIRS_TEN:
                ItemManager.unlock(ITEM.KEY, ITEM.FOX);
                break;
            case STAIRS_TWENTY:
                ItemManager.unlock(ITEM.CAPE, ITEM.WITCHHAT, ITEM.ARMOR);
                break;
            case STAIRS_THIRTY:
                ItemManager.unlock(ITEM.TWOSWORD, ITEM.HUENKEL, ITEM.WAND);
                break;
            case STAIRS_FOURTY:
                ItemManager.unlock(ITEM.AXS, ITEM.EXS);
                break;
            case ALL_PLAYER_KILLED:
                ItemManager.unlock(ITEM.RIBON, ITEM.LIFEGAIN);
                break;
            case OUTSIDE_FIVE_TIMES:
                ItemManager.unlock(ITEM.CHRONICLES);
                break;
        }
    }

    @Deprecated
    public static void achieveAllDebug() {
        ItemManager.unlock(ITEM.MEDHICAL, ITEM.PUCHIRING, ITEM.FLAG);
        ItemManager.unlock(ITEM.BOOK, ITEM.BOOTS, ITEM.SHIELD, ITEM.BILL);
        ItemManager.unlock(ITEM.KEY, ITEM.FOX);
        ItemManager.unlock(ITEM.CAPE, ITEM.WITCHHAT, ITEM.ARMOR);
        ItemManager.unlock(ITEM.TWOSWORD, ITEM.HUENKEL, ITEM.WAND);
        ItemManager.unlock(ITEM.AXS, ITEM.EXS);
        ItemManager.unlock(ITEM.RIBON, ITEM.LIFEGAIN);
        ItemManager.unlock(ITEM.CHRONICLES);
    }
}
