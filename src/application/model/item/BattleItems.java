package application.model.item;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import dto.chara.abs.Characters;
import dto.item.enums.ITEM;
import javafx.scene.control.CheckBox;
import lombok.Getter;

public class BattleItems {

    @Getter
    private static Set<ITEM> _Items;

    static {
        _Items = new HashSet<>();
    }

    public static void clearThenSetItems(List<CheckBox> selectedList) {

        clearItems();

        List<CheckBox> collect =
                selectedList.stream().filter(s -> s.isSelected()).collect(Collectors.toList());
        for (CheckBox chkBox : collect) {
            _Items.add(ITEM.getEnum(getId(chkBox)));
        }
    }

    public static void clearItems() {
        // 連打での重複されると困るので毎回初期化する
        _Items.clear();
    }

    private static int getId(CheckBox cbox) {
        return Integer.parseInt(cbox.getId().substring(9));
    }

    public static void affectItems(Characters[] charas) {
        for (ITEM item : _Items) {
            for (Characters characters : charas) {
                item.affect(characters);
            }
        }
    }

}
