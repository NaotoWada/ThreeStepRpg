package application.ctrl;

import java.net.URL;
import java.util.ResourceBundle;
import application.model.exp.ExperienceManager;
import application.model.money.MoneyManager;
import application.model.node.scene.SceneManager;
import application.model.node.stage.StageManager;
import dto.chara.manage.PartyManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import lombok.Getter;
import resource.audio.MediaController.MEDIA;
import resource.audio.SoundEffectController.SE;
import resource.css.CssManager;
import resource.fxml.manage.FXMLManager;
import resource.img.PaneAnimationManager;
import resource.img.enums.EVENT_TYPE;

public class TopMenuController implements Initializable {

    @Getter
    private static final TopMenuController instance = new TopMenuController();

    @FXML
    private Button createPatry;
    @FXML
    private Button choseItem;
    @FXML
    private Button outside;
    @FXML
    private Button debug;

    @FXML
    private Label infoLabel;
    @FXML
    private Label textLabel;

    @FXML
    private Pane paneChara1;
    @FXML
    private Pane paneChara2;
    @FXML
    private Pane paneChara3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PaneAnimationManager.playAllAnimationChara(EVENT_TYPE.ANYTIME, paneChara1, paneChara2,
                paneChara3);
    }

    public void changeThenShow() {

        MEDIA.BATTLE.stop();
        MEDIA.TOP_MENU.play();

        Parent uri = FXMLManager.getResource(this);
        Scene scene = SceneManager.newScene(uri);
        CssManager.addStyleSheet(scene, "conf/css/TopMenu.css");
        StageManager.changeThenShow(scene);
    }

    /**
     * パーティ作成ボタンクリック時イベント.
     * <p>
     */
    @FXML
    public void onPartyCreateClicked() {
        SE.DECISION.play();
        CreatePartyController.getInstance().changeThenShow();
    }

    /**
     * アイテム選択ボタンクリック時イベント.
     * <p>
     */
    @FXML
    public void onChoseItemClicked() {
        SE.DECISION.play();
        ChoseItemController.getInstance().changeThenShow();
    }

    /**
     * デバッグ用
     * <p>
     */
    @FXML
    @Deprecated
    public void onDebugClicked() {
        long exp = ExperienceManager.get_Experience();
        long mny = MoneyManager.get_Money();
        textLabel.textProperty().set(debug(exp, mny));
    }

    private String debug(long exp, long mny) {
        StringBuilder sb = new StringBuilder();
        sb.append("経験値[");
        sb.append(exp);
        sb.append("] お金[");
        sb.append(mny);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 外出ボタンクリック時イベント.
     * <p>
     */
    @FXML
    public void onOutsideClicked() {
        if (PartyManager.isNothingPlayer()) {
            // パーティが0人
            SE.CANCEL.play();
            infoLabel.setText("パーティメンバーが1人もいないので冒険できません");
            return;

        } else {
            // それ以外
            SE.OUTSIDE.playWhile(1200L);
            BattleController.getInstance().changeThenShow();
        }
    }
}
