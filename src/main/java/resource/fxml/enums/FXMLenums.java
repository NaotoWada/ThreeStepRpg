package resource.fxml.enums;

import application.ctrl.BattleController;
import application.ctrl.ChoseItemController;
import application.ctrl.CreatePartyController;
import application.ctrl.CreateSingleCharaController;
import application.ctrl.TopMenuController;

public enum FXMLenums {
    BATTLE(BattleController.class, "conf/xml/battleScene.fxml"), //
    CHOSE_ITEM(ChoseItemController.class, "conf/xml/choseItem.fxml"), //
    PARTY_MENU(CreatePartyController.class, "conf/xml/createParty.fxml"), //
    SINGLE_CREATE(CreateSingleCharaController.class, "conf/xml/createSingleChara.fxml"), //
    TOP_MENU(TopMenuController.class, "conf/xml/topMenu.fxml"),;

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
