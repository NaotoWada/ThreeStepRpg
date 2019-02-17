package application.model.charamenu;

import application.ctrl.CreateSingleCharaController;
import application.model.item.BattleItems;
import application.model.node.pane.PaneUtils;
import dto.chara.manage.PartyManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Pane;
import resource.audio.SoundEffectController.SE;
import resource.img.PaneAnimationManager;

public class CharacterMenuAction {

    private StringProperty _ValueProperty;

    public CharacterMenuAction() {
        System.out.println("CharacterMenuAction");
        _ValueProperty = new SimpleStringProperty();
    }

    public StringProperty valueProperty() {
        return _ValueProperty;
    }

    public void executeCreate(Pane p) {

        // 既に作成済み
        if (PartyManager.isAlreadyCreate(PaneUtils.getId(p))) {
            SE.CANCEL.play();
            _ValueProperty.set("既に作成済みです");
            return;
        } else {

            // 未作成か削除
            SE.DECISION.play();
            CreateSingleCharaController.getInstance().changeThenShow(PaneUtils.getId(p));
        }
    }

    public void executeDelete(Pane p) {

        int id = PaneUtils.getId(p);
        if (PartyManager.isAlreadyCreate(id)) {

            // 作成したスロットから削除する
            SE.DECISION.play();
            PartyManager.remove(id);
            PaneAnimationManager.removeAnimationChara(id);

            p.getChildren().clear();
            // アイテムの所持がキャラクターによって変わるので削除する
            BattleItems.clearItems();
            _ValueProperty.set("キャラクターを削除しました");
        } else {

            // スロットが空なので削除する必要がない
            SE.CANCEL.play();
            _ValueProperty.set("既に削除済みです");
        }
    }
}
