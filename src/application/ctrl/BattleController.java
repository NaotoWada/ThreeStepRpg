package application.ctrl;

import java.net.URL;
import java.util.ResourceBundle;
import application.model.battle.BattleService;
import application.model.battle.wave.WaveHelper;
import application.model.node.scene.SceneManager;
import application.model.node.stage.StageManager;
import dto.achieve.AchieveManager;
import dto.achieve.enums.EVENT;
import dto.chara.manage.PartyManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import lombok.Getter;
import resource.audio.MediaController.MEDIA;
import resource.css.CssManager;
import resource.fxml.manage.FXMLManager;
import resource.img.PaneAnimationManager;
import resource.img.enums.EVENT_TYPE;

public class BattleController implements Initializable {

    @Getter
    private static BattleController instance = new BattleController();

    private final BattleService battleService = BattleService.getInstance();

    @FXML
    protected Label textLabel;

    @FXML
    protected Pane paneChara1;
    @FXML
    protected Pane paneChara2;
    @FXML
    protected Pane paneChara3;

    @FXML
    protected HBox num1HBox;

    @FXML
    private Pane num1_1;
    @FXML
    private Pane num1_2;
    @FXML
    private Pane num1_3;
    @FXML
    private Pane num1_4;
    @FXML
    private Pane num1_5;
    @FXML
    private Pane num1_6;
    @FXML
    private Pane num1_7;
    @FXML
    private Pane num1_8;
    @FXML
    private Pane num1_9;

    @FXML
    private Pane num2_1;
    @FXML
    private Pane num2_2;
    @FXML
    private Pane num2_3;
    @FXML
    private Pane num2_4;
    @FXML
    private Pane num2_5;
    @FXML
    private Pane num2_6;
    @FXML
    private Pane num2_7;
    @FXML
    private Pane num2_8;
    @FXML
    private Pane num2_9;

    @FXML
    private Pane num3_1;
    @FXML
    private Pane num3_2;
    @FXML
    private Pane num3_3;
    @FXML
    private Pane num3_4;
    @FXML
    private Pane num3_5;
    @FXML
    private Pane num3_6;
    @FXML
    private Pane num3_7;
    @FXML
    private Pane num3_8;
    @FXML
    private Pane num3_9;

    @FXML
    private Pane numMons_1;
    @FXML
    private Pane numMons_2;
    @FXML
    private Pane numMons_3;
    @FXML
    private Pane numMons_4;
    @FXML
    private Pane numMons_5;
    @FXML
    private Pane numMons_6;
    @FXML
    private Pane numMons_7;
    @FXML
    private Pane numMons_8;
    @FXML
    private Pane numMons_9;

    @FXML
    private ProgressBar hpBarChara1;
    @FXML
    private ProgressBar hpBarChara2;
    @FXML
    private ProgressBar hpBarChara3;
    @FXML
    private ProgressBar hpBarMonster;
    @FXML
    private ProgressBar mpBarChara1;
    @FXML
    private ProgressBar mpBarChara2;
    @FXML
    private ProgressBar mpBarChara3;

    @FXML
    protected Pane monsterPane;
    @FXML
    protected Label stairsLabel;
    @FXML
    protected Label turnLabel;
    @FXML
    protected ImageView turnImg;
    @FXML
    protected Label rankLabel;
    @FXML
    protected ImageView rankImg;

    @FXML
    private Button muteButton;
    @FXML
    private Button soundButton;
    @FXML
    private TextArea textArea;

    @FXML
    public void onBackHomeClicked() {
        TopMenuController.getInstance().changeThenShow();
        battleService.stop();
    }

    @FXML
    public void onMuteClicked() {
        MEDIA.BATTLE.mute();
        muteButton.visibleProperty().set(false);
        soundButton.visibleProperty().set(true);
    }

    @FXML
    public void onSoundClicked() {
        MEDIA.BATTLE.restart();
        muteButton.visibleProperty().set(true);
        soundButton.visibleProperty().set(false);
    }

    public void changeThenShow() {
        MEDIA.TOP_MENU.stop();
        MEDIA.BATTLE.play();

        Parent uri = FXMLManager.getResource(this);
        Scene scene = SceneManager.newScene(uri);
        CssManager.addStyleSheet(scene, "Battle.css");
        StageManager.changeThenShow(scene);

        battleService.start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // 戦闘用のフィールドに設定して描写
        PartyManager.copyToBattle();
        PaneAnimationManager.copyToBattle();
        PaneAnimationManager.playAllAnimationChara(EVENT_TYPE.BATTLE, paneChara1, paneChara2,
                paneChara3);

        // 初外出の解放
        AchieveManager.achieve(EVENT.FIRST_OUTSIDE);

        monsterPane.toBack();
        allocatePanes();
        allocateProgress();
        allocateNumPanes();

        WaveHelper.changeEnemy();

        // バインド
        stairsLabel.textProperty().bind(battleService.textStairsProperty());
        turnLabel.textProperty().bind(battleService.textTurnProperty());
        turnImg.toBack();
        rankLabel.textProperty().bind(battleService.textRankProperty());
        rankImg.toBack();
        muteButton.visibleProperty().set(true);
        soundButton.visibleProperty().set(false);
        textArea.textProperty().bind(battleService.textAreaProperty());
    }

    private void allocatePanes() {
        battleService.allocatePlayerPane(paneChara1, paneChara2, paneChara3);
        BattleService.set_MonsBattlePane(monsterPane);
    }

    private void allocateProgress() {
        BattleService.set_HpBarChara1(hpBarChara1);
        BattleService.set_HpBarChara2(hpBarChara2);
        BattleService.set_HpBarChara3(hpBarChara3);
        BattleService.set_MpBarChara1(mpBarChara1);
        BattleService.set_MpBarChara2(mpBarChara2);
        BattleService.set_MpBarChara3(mpBarChara3);
        BattleService.set_HpBarMonster(hpBarMonster);
    }

    private void allocateNumPanes() {
        battleService.allocateNumPaneChara(num1_1, num1_2, num1_3, num1_4, num1_5, num1_6, num1_7,
                num1_8, num1_9, num2_1, num2_2, num2_3, num2_4, num2_5, num2_6, num2_7, num2_8,
                num2_9, num3_1, num3_2, num3_3, num3_4, num3_5, num3_6, num3_7, num3_8, num3_9);
        battleService.allocateNumPaneMonster(numMons_1, numMons_2, numMons_3, numMons_4, numMons_5,
                numMons_6, numMons_7, numMons_8, numMons_9);
    }
}
