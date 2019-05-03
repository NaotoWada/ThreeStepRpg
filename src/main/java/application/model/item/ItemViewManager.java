package application.model.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import application.ctrl.ChoseItemController;
import application.model.item.validate.Validator;
import application.model.money.MoneyManager;
import application.model.node.pane.PaneUtils;
import dto.chara.enums.JOBManage.JOB;
import dto.item.ItemManager;
import dto.item.enums.ITEM;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import resource.audio.SoundEffectController.SE;

public class ItemViewManager {

    private StringProperty textProperty;
    private StringProperty textSelectedProperty;
    private BooleanProperty selectedProperty;

    public ItemViewManager() {
        textProperty = new SimpleStringProperty();
        textSelectedProperty = new SimpleStringProperty();
        selectedProperty = new SimpleBooleanProperty();
    }

    public StringProperty textLabelProperty() {
        return textProperty;
    }

    public StringProperty textSelectedProperty() {
        return textSelectedProperty;
    }

    public BooleanProperty selectedProperty() {
        return selectedProperty;
    }

    public void initiate() {
        initItemVisible();
        initTextBox();
    }

    private void initItemVisible() {
        toVisibleFalsePane();
        toVisibleFalseChkBox();
        toVisibleTrueAsUnlocks();
        toSelectedCheck();
        toSelectedAllCheck();
        setViewItem();
    }

    private void toVisibleFalsePane() {
        ChoseItemController.get_PaneList().stream().forEach(s -> s.setVisible(false));
    }

    private void toVisibleFalseChkBox() {
        ChoseItemController.get_CheckList().stream().forEach(s -> s.setVisible(false));
    }

    private void toVisibleTrueAsUnlocks() {
        Set<Integer> unlocks = ItemManager.getUnlocks();
        for (Integer elm : unlocks) {
            ChoseItemController.get_PaneList().get(elm).visibleProperty().set(true);
            ChoseItemController.get_CheckList().get(elm).visibleProperty().set(true);
        }
    }

    private void toSelectedCheck() {
        Set<ITEM> selected = BattleItems.get_Items();
        for (ITEM item : selected) {
            ChoseItemController.get_CheckList().get(item.ordinal()).selectedProperty().set(true);
        }
    }

    private void toSelectedAllCheck() {
        if (this.isAllSelected()) {
            this.selectedProperty.setValue(true);
        } else {
            this.selectedProperty.setValue(false);
        }
    }

    private void setViewItem() {
        int loopCnt = 0;
        for (ITEM item : ITEM.values()) {
            String uri = ClassLoader.getSystemResource(item.getPath()).toExternalForm();
            ImageView img = new ImageView(uri);
            ChoseItemController.get_PaneList().get(loopCnt).getChildren().add(img);
            loopCnt++;
        }
    }

    private void initTextBox() {
        Set<Integer> unlocks = ItemManager.getUnlocks();
        if (unlocks.isEmpty()) {
            textProperty.setValue("まだ表示するアイテムがないようだ。");
        } else {
            Set<ITEM> alreadySet = BattleItems.get_Items();
            if (alreadySet.isEmpty()) {
                textSelectedProperty.setValue("選択中のアイテムはありません");
            } else {
                textSelectedProperty.setValue(makeTextLineFeed(alreadySet));
            }
        }
    }

    private String makeTextLineFeed(Set<ITEM> alreadySet) {
        int addCnt = 1;
        StringBuilder sb = new StringBuilder();
        for (ITEM itemEnum : alreadySet) {
            String elm = itemEnum.getName();
            sb.append(elm);
            sb.append(",");
            if (addCnt % 8 == 0) {
                sb.append("\r\n");
            }
            addCnt++;
        }
        return sb.toString();
    }

    public void setText() {
        List<String> selected = getSelectedItem();
        String text = makeTextLineFeed(selected);
        textSelectedProperty.setValue(text);
    }

    private String makeTextLineFeed(List<String> selected) {
        int addCnt = 1;
        StringBuilder sb = new StringBuilder();
        for (String elm : selected) {
            sb.append(elm);
            sb.append(",");
            if (addCnt % 8 == 0) {
                sb.append("\r\n");
            }
            addCnt++;
        }
        return sb.toString();
    }

    private List<String> getSelectedItem() {
        List<String> selected = new ArrayList<>();
        for (CheckBox cBox : ChoseItemController.get_CheckList()) {
            if (!cBox.isSelected()) {
                continue;
            }
            selected.add(ITEM.getEnum(PaneUtils.getId(cBox)).getName());
        }
        return selected;
    }

    private boolean isAllSelected() {
        return ChoseItemController.get_CheckList().stream().allMatch(s -> s.isSelected());
    }


    public void check(int i) {

        if (!hasEnoughMoney(i)) {
            ChoseItemController.get_CheckList().get(i).selectedProperty().set(false);
            return;
        }

        SE.CURSOR.play();
        textProperty.set("チェックしました");
    }

    public void checkIfExistJob(JOB job, int i) {
        switch (job) {
            case ARCHER_F:
                break;
            case CHIEF_M:
                if (Validator.canSelectChief()) {
                    SE.CURSOR.play();
                    textProperty.set("チェックしました");
                } else {
                    SE.CANCEL.play();
                    textProperty.set("シーフ系がパーティにいないので設定できません！");
                    ChoseItemController.get_CheckList().get(i).selectedProperty().set(false);
                }
                break;
            case PRIEST_F:
                if (Validator.canSelectPriest()) {
                    SE.CURSOR.play();
                    textProperty.set("チェックしました");
                } else {
                    SE.CANCEL.play();
                    textProperty.set("僧侶系がパーティにいないので設定できません！");
                    ChoseItemController.get_CheckList().get(i).selectedProperty().set(false);
                }
                break;
            case WARRIOR_F:
            case WARRIOR_M:
                if (Validator.canSelectWarrior()) {
                    SE.CURSOR.play();
                    textProperty.set("チェックしました");
                } else {
                    SE.CANCEL.play();
                    textProperty.set("戦士系がパーティにいないので設定できません！");
                    ChoseItemController.get_CheckList().get(i).selectedProperty().set(false);
                }
                break;
            case WITCH_F:
                if (Validator.canSelectWitch()) {
                    SE.CURSOR.play();
                    textProperty.set("チェックしました");
                } else {
                    SE.CANCEL.play();
                    textProperty.set("ウィッチ系がパーティにいないので設定できません！");
                    ChoseItemController.get_CheckList().get(i).selectedProperty().set(false);
                }
                break;
            default:
                break;
        }
    }

    public boolean hasEnoughMoney(int i) {
        long itm = ITEM.getEnum(i).getMoney();
        long own = MoneyManager.get_Money();
        if (itm < own) {
            return true;
        } else {
            textProperty.set("所持金が足りません 所持金[" + own + "] アイテム費用[" + itm + "]");
            return false;
        }
    }

}
