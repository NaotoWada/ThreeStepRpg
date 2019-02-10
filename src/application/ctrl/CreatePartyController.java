package application.ctrl;

import java.net.URL;
import java.util.ResourceBundle;
import application.model.charamenu.CharacterMenuAction;
import application.model.node.scene.SceneManager;
import application.model.node.stage.StageManager;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import lombok.Getter;
import resource.audio.SoundEffectController.SE;
import resource.css.CssManager;
import resource.fxml.manage.FXMLManager;
import resource.img.PaneAnimationManager;
import resource.img.enums.EVENT_TYPE;


public class CreatePartyController implements Initializable {

    @Getter
    private static final CreatePartyController instance = new CreatePartyController();

    private CharacterMenuAction action = new CharacterMenuAction();

    @FXML
    private Button buttonCharaCreate1;
    @FXML
    private Button buttonCharaCreate2;
    @FXML
    private Button buttonCharaCreate3;

    @FXML
    private Button buttonCharaDel1;
    @FXML
    private Button buttonCharaDel2;
    @FXML
    private Button buttonCharaDel3;

    @FXML
    private Pane paneChara1;
    @FXML
    private Pane paneChara2;
    @FXML
    private Pane paneChara3;

    @FXML
    private Button buttonReturn;

    @FXML
    private Label infoLabel;
    @FXML
    private Label textLabel;

    @FXML
    public void onFirstCharaCreateClicked(Event e) {
        action.executeCreate(paneChara1);
    }

    @FXML
    public void onSecondCharaCreateClicked() {
        action.executeCreate(paneChara2);
    }

    @FXML
    public void onThirdCharaCreateClicked() {
        action.executeCreate(paneChara3);
    }

    @FXML
    public void onFirstCharaDelClicked() {
        action.executeDelete(paneChara1);
    }

    @FXML
    public void onSecondCharaDelClicked() {
        action.executeDelete(paneChara2);
    }

    @FXML
    public void onThirdCharaDelClicked() {
        action.executeDelete(paneChara3);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PaneAnimationManager.playAllAnimationChara(EVENT_TYPE.ANYTIME, paneChara1, paneChara2,
                paneChara3);

        // バインド
        textLabel.textProperty().bind(action.valueProperty());
    }

    public void changeThenShow() {
        Parent uri = FXMLManager.getResource(this);
        Scene scene = SceneManager.newScene(uri);
        CssManager.addStyleSheet(scene, "CreateParty.css");
        StageManager.changeThenShow(scene);
    }

    @FXML
    public void onReturnClicked() {
        SE.CANCEL.play();
        TopMenuController.getInstance().changeThenShow();
    }

}
