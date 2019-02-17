package application.ctrl;

import java.net.URL;
import java.util.ResourceBundle;
import application.model.charamenu.SingleCharaMenuAction;
import application.model.charamenu.enums.COMMAND;
import application.model.node.scene.SceneManager;
import application.model.node.stage.StageManager;
import dto.chara.enums.JOBManage.JOB_PARAM_STATUS;
import dto.chara.enums.STATUS;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;
import resource.audio.SoundEffectController.SE;
import resource.css.CssManager;
import resource.fxml.manage.FXMLManager;
import resource.img.FullSizeAnimeManager;
import resource.img.ImageAnimationView;
import resource.img.enums.NAVI;


public class CreateSingleCharaController implements Initializable {

    @Getter
    private static CreateSingleCharaController instance = new CreateSingleCharaController();

    private SingleCharaMenuAction action = new SingleCharaMenuAction();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // 初期描写
        FullSizeAnimeManager.getInstance().initFullSize();
        ImageAnimationView img = FullSizeAnimeManager.getNowFullSizeImage();
        img.play();
        jobPane.getChildren().add(img);

        // 説明欄とステータスの初期化
        action.changeExplain();
        action.setJobName();
        action.resetWholeStatus();

        // バインド
        jobName.textProperty().bind(action.jobNameProperty());
        textExplainJob.textProperty().bind(action.jobTextProperty());
        textLabel.textProperty().bind(action.textLabelProperty());
        remainPoint.textProperty().bind(action.remainProperty());

        action.playerNameProperty().bind(playerNameField.textProperty());

        setStatusBar();
    }

    private void setStatusBar() {
        JOB_PARAM_STATUS sts = FullSizeAnimeManager.getCreationInfo().getJob().getSts();
        strBar.setProgress(sts.getStrn());
        intBar.setProgress(sts.getIntl());
        vitBar.setProgress(sts.getVitl());
        spdBar.setProgress(sts.getSped());
        accBar.setProgress(sts.getAccr());
        lukBar.setProgress(sts.getLuck());
    }

    public void changeThenShow(int createTarget) {
        set_TargetCharaPoint(createTarget);
        Parent uri = FXMLManager.getResource(this);
        Scene scene = SceneManager.newScene(uri);
        CssManager.addStyleSheet(scene, "conf/css/CreateSingleChara.css");
        StageManager.changeThenShow(scene);
    }

    // 職名、画像
    @FXML
    private Label jobName;
    @FXML
    private Pane jobPane;
    @FXML
    private TextField playerNameField;

    // 職操作：左右
    @FXML
    private Button buttonLeftJob;
    @FXML
    private Button buttonRightJob;

    // ステータス操作：左
    @FXML
    private Button buttonLeftSTR;
    @FXML
    private Button buttonLeftINT;
    @FXML
    private Button buttonLeftVIT;
    @FXML
    private Button buttonLeftSPD;
    @FXML
    private Button buttonLeftACC;
    @FXML
    private Button buttonLeftLUK;

    // ステータス操作：右
    @FXML
    private Button buttonRightSTR;
    @FXML
    private Button buttonRightINT;
    @FXML
    private Button buttonRightVIT;
    @FXML
    private Button buttonRightSPD;
    @FXML
    private Button buttonRightACC;
    @FXML
    private Button buttonRightLUK;

    // ステータス操作：右
    @FXML
    private ProgressBar strBar;
    @FXML
    private ProgressBar intBar;
    @FXML
    private ProgressBar vitBar;
    @FXML
    private ProgressBar spdBar;
    @FXML
    private ProgressBar accBar;
    @FXML
    private ProgressBar lukBar;

    // 作成と戻る
    @FXML
    private Button buttonCreate;
    @FXML
    private Button buttonReturn;

    // テキストベース
    @FXML
    private Label textLabel;
    @FXML
    private Label textExplainJob;
    @FXML
    private Label remainPoint;

    @Getter
    @Setter
    private static int _TargetCharaPoint;

    @FXML
    public void onCreateClicked() {
        SE.CALL.play();
        action.create(get_TargetCharaPoint());
        CreatePartyController.getInstance().changeThenShow();
    }

    @FXML
    public void onLeftJobClicked() {
        SE.CURSOR.play();
        action.execute(jobPane, NAVI.LEFT);
        setStatusBar();
    }

    @FXML
    public void onRightJobClicked() {
        SE.CURSOR.play();
        action.execute(jobPane, NAVI.RIGHT);
        setStatusBar();
    }

    // プログレスバーに細かくバインドする方法が見つからなかったのでコントローラーで設定する
    // 別クラスで実行可否が決まるので、呼び出し先で音声効果を出力する
    @FXML
    public void onLeftSTRClicked() {
        action.changeSts(STATUS.STR, COMMAND.DECREASE);
        strBar.setProgress(action.getSts(STATUS.STR));
    }

    @FXML
    public void onRightSTRClicked() {
        action.changeSts(STATUS.STR, COMMAND.INCREASE);
        strBar.setProgress(action.getSts(STATUS.STR));
    }

    @FXML
    public void onLeftINTClicked() {
        action.changeSts(STATUS.INT, COMMAND.DECREASE);
        intBar.setProgress(action.getSts(STATUS.INT));
    }

    @FXML
    public void onRightINTClicked() {
        action.changeSts(STATUS.INT, COMMAND.INCREASE);
        intBar.setProgress(action.getSts(STATUS.INT));
    }

    @FXML
    public void onLeftVITClicked() {
        action.changeSts(STATUS.VIT, COMMAND.DECREASE);
        vitBar.setProgress(action.getSts(STATUS.VIT));
    }

    @FXML
    public void onRightVITClicked() {
        action.changeSts(STATUS.VIT, COMMAND.INCREASE);
        vitBar.setProgress(action.getSts(STATUS.VIT));
    }

    @FXML
    public void onLeftSPDClicked() {
        action.changeSts(STATUS.SPD, COMMAND.DECREASE);
        spdBar.setProgress(action.getSts(STATUS.SPD));
    }

    @FXML
    public void onRightSPDClicked() {
        action.changeSts(STATUS.SPD, COMMAND.INCREASE);
        spdBar.setProgress(action.getSts(STATUS.SPD));
    }

    @FXML
    public void onLeftACCClicked() {
        action.changeSts(STATUS.ACC, COMMAND.DECREASE);
        accBar.setProgress(action.getSts(STATUS.ACC));
    }

    @FXML
    public void onRightACCClicked() {
        action.changeSts(STATUS.ACC, COMMAND.INCREASE);
        accBar.setProgress(action.getSts(STATUS.ACC));
    }

    @FXML
    public void onLeftLUKClicked() {
        action.changeSts(STATUS.LUK, COMMAND.DECREASE);
        lukBar.setProgress(action.getSts(STATUS.LUK));
    }

    @FXML
    public void onRightLUKClicked() {
        action.changeSts(STATUS.LUK, COMMAND.INCREASE);
        lukBar.setProgress(action.getSts(STATUS.LUK));
    }

    @FXML
    public void onReturnClicked() {
        SE.CANCEL.play();
        CreatePartyController.getInstance().changeThenShow();
    }
}
