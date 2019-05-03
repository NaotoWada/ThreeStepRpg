package application.ctrl;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import application.model.item.BattleItems;
import application.model.item.ItemToolTipManager;
import application.model.item.ItemViewManager;
import application.model.node.scene.SceneManager;
import application.model.node.stage.StageManager;
import dto.chara.enums.JOBManage.JOB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import lombok.Getter;
import resource.audio.SoundEffectController.SE;
import resource.css.CssManager;
import resource.fxml.manage.FXMLManager;
import resource.img.PaneAnimationManager;
import resource.img.enums.EVENT_TYPE;


public class ChoseItemController implements Initializable {

    @Getter
    private static ChoseItemController instance = new ChoseItemController();

    private ItemViewManager _ItemViewManager = new ItemViewManager();
    private ItemToolTipManager _ItemToolTipManager = new ItemToolTipManager();

    @Getter
    private static List<Pane> _PaneList = new ArrayList<>();
    @Getter
    private static List<CheckBox> _CheckList = new ArrayList<>();

    @FXML
    public void onDecideClicked() {
        SE.ITEM_SET.play();
        _ItemViewManager.setText();
        BattleItems.clearThenSetItems(_CheckList);
    }

    @FXML
    public void onReturnClicked() {
        SE.CANCEL.play();
        TopMenuController.getInstance().changeThenShow();
    }

    public void changeThenShow() {
        Parent uri = FXMLManager.getResource(this);
        Scene scene = SceneManager.newScene(uri);
        CssManager.addStyleSheet(scene, "conf/css/ChoseItem.css");
        StageManager.changeThenShow(scene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PaneAnimationManager.playAllAnimationChara(EVENT_TYPE.ANYTIME, paneChara1, paneChara2,
                paneChara3);
        itemSelectViewLabel.toBack();

        // これ以降の関数呼び出しに前後関係がある。これより前にItemのマネジャーを呼び出すとまずい
        this.resetPaneAndChkBox();

        _ItemViewManager.initiate(); // 100ms程度処理がかかる
        _ItemToolTipManager.initToolTip();

        // バインド
        textLabel.textProperty().bind(_ItemViewManager.textLabelProperty());
        itemSelectedLabel.textProperty().bind(_ItemViewManager.textSelectedProperty());
    }

    private void resetPaneAndChkBox() {
        // 毎回クリアして洗い替えする事でvisibleを気にしなくてよくなる
        _PaneList.clear();
        _PaneList.addAll(Arrays.asList(itemPane0, itemPane1, itemPane2, itemPane3, itemPane4,
                itemPane5, itemPane6, itemPane7, itemPane8, itemPane9, itemPane10, itemPane11,
                itemPane12, itemPane13, itemPane14, itemPane15, itemPane16, itemPane17, itemPane18,
                itemPane19));

        _CheckList.clear();
        _CheckList.addAll(Arrays.asList(checkItem0, checkItem1, checkItem2, checkItem3, checkItem4,
                checkItem5, checkItem6, checkItem7, checkItem8, checkItem9, checkItem10,
                checkItem11, checkItem12, checkItem13, checkItem14, checkItem15, checkItem16,
                checkItem17, checkItem18, checkItem19));
    }

    // 作成と戻る
    @FXML
    private Button buttonCreate;
    @FXML
    private Button buttonReturn;

    @FXML
    private Label textLabel;

    @FXML
    private Label itemSelectViewLabel;
    @FXML
    private Label itemSelectedLabel;

    @FXML
    private Pane itemPane0;
    @FXML
    private Pane itemPane1;
    @FXML
    private Pane itemPane2;
    @FXML
    private Pane itemPane3;
    @FXML
    private Pane itemPane4;
    @FXML
    private Pane itemPane5;
    @FXML
    private Pane itemPane6;
    @FXML
    private Pane itemPane7;
    @FXML
    private Pane itemPane8;
    @FXML
    private Pane itemPane9;
    @FXML
    private Pane itemPane10;
    @FXML
    private Pane itemPane11;
    @FXML
    private Pane itemPane12;
    @FXML
    private Pane itemPane13;
    @FXML
    private Pane itemPane14;
    @FXML
    private Pane itemPane15;
    @FXML
    private Pane itemPane16;
    @FXML
    private Pane itemPane17;
    @FXML
    private Pane itemPane18;
    @FXML
    private Pane itemPane19;

    @FXML
    private CheckBox checkItem0;
    @FXML
    private CheckBox checkItem1;
    @FXML
    private CheckBox checkItem2;
    @FXML
    private CheckBox checkItem3;
    @FXML
    private CheckBox checkItem4;
    @FXML
    private CheckBox checkItem5;
    @FXML
    private CheckBox checkItem6;
    @FXML
    private CheckBox checkItem7;
    @FXML
    private CheckBox checkItem8;
    @FXML
    private CheckBox checkItem9;
    @FXML
    private CheckBox checkItem10;
    @FXML
    private CheckBox checkItem11;
    @FXML
    private CheckBox checkItem12;
    @FXML
    private CheckBox checkItem13;
    @FXML
    private CheckBox checkItem14;
    @FXML
    private CheckBox checkItem15;
    @FXML
    private CheckBox checkItem16;
    @FXML
    private CheckBox checkItem17;
    @FXML
    private CheckBox checkItem18;
    @FXML
    private CheckBox checkItem19;

    @FXML
    private Pane paneChara1;
    @FXML
    private Pane paneChara2;
    @FXML
    private Pane paneChara3;

    @FXML
    public void onCheckItem0Clicked() {
        // 薬草
        _ItemViewManager.check(0);
    }

    @FXML
    public void onCheckItem1Clicked() {
        // 指輪
        _ItemViewManager.check(1);
    }

    @FXML
    public void onCheckItem2Clicked() {
        // 白旗
        _ItemViewManager.check(2);
    }

    @FXML
    public void onCheckItem3Clicked() {
        // お札
        _ItemViewManager.check(3);
    }

    @FXML
    public void onCheckItem4Clicked() {
        // スキルブック
        _ItemViewManager.check(4);
    }

    @FXML
    public void onCheckItem5Clicked() {
        // 革ブーツ
        _ItemViewManager.check(5);
    }

    @FXML
    public void onCheckItem6Clicked() {
        // 革シールド
        _ItemViewManager.check(6);
    }

    @FXML
    public void onCheckItem7Clicked() {
        // 銀のカギ
        _ItemViewManager.checkIfExistJob(JOB.CHIEF_M, 7);
    }

    @FXML
    public void onCheckItem8Clicked() {
        // 狐の面
        _ItemViewManager.check(8);
    }

    @FXML
    public void onCheckItem9Clicked() {
        // ローブ
        _ItemViewManager.check(9);
    }

    @FXML
    public void onCheckItem10Clicked() {
        // ウィッチハット
        _ItemViewManager.checkIfExistJob(JOB.WITCH_F, 10);
    }

    @FXML
    public void onCheckItem11Clicked() {
        // プレートアーマー
        _ItemViewManager.check(11);
    }

    @FXML
    public void onCheckItem12Clicked() {
        // 二刀
        _ItemViewManager.checkIfExistJob(JOB.CHIEF_M, 12);
    }

    @FXML
    public void onCheckItem13Clicked() {
        // ヒュンケル
        _ItemViewManager.checkIfExistJob(JOB.ARCHER_F, 13);
    }

    @FXML
    public void onCheckItem14Clicked() {
        // シャドウスタッフ
        _ItemViewManager.checkIfExistJob(JOB.WITCH_F, 14);
    }

    @FXML
    public void onCheckItem15Clicked() {
        // ハートブレイカー
        _ItemViewManager.checkIfExistJob(JOB.WARRIOR_F, 15);
    }

    @FXML
    public void onCheckItem16Clicked() {
        // エクスカリバー
        _ItemViewManager.checkIfExistJob(JOB.WARRIOR_F, 16);
    }

    @FXML
    public void onCheckItem17Clicked() {
        // リボン
        _ItemViewManager.check(17);
    }

    @FXML
    public void onCheckItem18Clicked() {
        // ライフストリーム
        _ItemViewManager.check(18);
    }

    @FXML
    public void onCheckItem19Clicked() {
        // とき時計
        _ItemViewManager.check(19);
    }

}
