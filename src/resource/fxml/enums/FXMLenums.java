package resource.fxml.enums;

import application.ctrl.BattleController;
import application.ctrl.ChoseItemController;
import application.ctrl.CreatePartyController;
import application.ctrl.CreateSingleCharaController;
import application.ctrl.TopMenuController;

public enum FXMLenums {
    BATTLE(BattleController.class, "battleScene.fxml"), //
    CHOSE_ITEM(ChoseItemController.class, "choseItem.fxml"), //
    PARTY_MENU(CreatePartyController.class, "createParty.fxml"), //
    SINGLE_CREATE(CreateSingleCharaController.class, "createSingleChara.fxml"), //
    TOP_MENU(TopMenuController.class, "topMenu.fxml"),;

    private final Class<?> clazz;
    private final String path;

    private FXMLenums(Class<?> clazz, String path) {
        this.path = path;
        this.clazz = clazz;
    }

    public static String getPath(Object obj) {

        for (FXMLenums elm : FXMLenums.values()) {
            if (elm.clazz == obj.getClass()) {
                return elm.path;
            } else {
                continue;
            }
        }
        throw new IllegalArgumentException("入力クラスに対応するパスが取得できませんでした。[" + obj + "]");
    }
}
