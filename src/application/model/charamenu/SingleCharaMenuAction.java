package application.model.charamenu;

import java.util.ResourceBundle;
import application.model.charamenu.enums.COMMAND;
import application.model.charamenu.sts.StatusChanger;
import application.model.charamenu.sts.StatusChanger.ChangeResult;
import application.thread.ThreadUtil;
import dto.chara.abs.CharaBuilder;
import dto.chara.abs.Characters;
import dto.chara.enums.EP_TYPE;
import dto.chara.enums.JOBManage.JOB;
import dto.chara.enums.JOBManage.JOB_PARAM_CORRECTION;
import dto.chara.enums.JOBManage.JOB_PARAM_STATUS;
import dto.chara.enums.STATUS;
import dto.chara.manage.PartyManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Pane;
import resource.fxml.manage.ResourceBundleUtf8Control;
import resource.img.FullSizeAnimeManager;
import resource.img.ImageAnimationView;
import resource.img.PaneAnimationManager;
import resource.img.enums.NAVI;

public class SingleCharaMenuAction {

    private CharaBuilder charaBuilder = new CharaBuilder();
    private StatusChanger changer = new StatusChanger();

    // ステータス値
    private int _Hp;
    private int _Mp;
    private int _Strength;
    private int _Intelligence;
    private int _Vitality;
    private int _Speed;
    private int _Accuracy;
    private int _Luck;

    // プロパティ
    private StringProperty jobNameProperty;
    private StringProperty jobExplainProperty;
    private StringProperty textLabelProperty;
    private StringProperty playerNameProperty;
    private StringProperty remainProperty;

    // ポイント
    private static final int _InitiateRemainPoint = 3;
    private static final int _CorrectionBias = 10;
    private int _RemainPoint;

    public SingleCharaMenuAction() {
        jobNameProperty = new SimpleStringProperty();
        jobExplainProperty = new SimpleStringProperty();
        textLabelProperty = new SimpleStringProperty();
        playerNameProperty = new SimpleStringProperty();
        _RemainPoint = _InitiateRemainPoint;
        remainProperty = new SimpleStringProperty(String.valueOf(_RemainPoint));
    }

    public StringProperty jobNameProperty() {
        return jobNameProperty;
    }

    public StringProperty jobTextProperty() {
        return jobExplainProperty;
    }

    public StringProperty textLabelProperty() {
        return textLabelProperty;
    }

    public StringProperty remainProperty() {
        return remainProperty;
    }

    public StringProperty playerNameProperty() {
        return playerNameProperty;
    }

    public void changeTextLabel(NAVI navi) {
        if (navi == NAVI.LEFT) {
            textLabelProperty.set("職業を左にシフト");
            setJobName();
        } else {
            textLabelProperty.set("職業を右にシフト");
            setJobName();
        }
    }

    public void resetWholeStatus() {
        _Hp = 50;
        _Mp = 10;
        JOB_PARAM_STATUS sts = FullSizeAnimeManager.getCreationInfo().getJob().getSts();
        _Strength = (int) (sts.getStrn() * 5);
        _Intelligence = (int) (sts.getIntl() * 5);
        _Vitality = (int) (sts.getVitl() * 5);
        _Speed = (int) (sts.getSped() * 5);
        _Accuracy = (int) (sts.getAccr() * 5);
        _Luck = (int) (sts.getLuck() * 5);
    }

    public void changeExplain() {
        String msgId = "EXPL00" + FullSizeAnimeManager.get_NowViewing();
        jobExplainProperty.set(getMessage(msgId));
    }

    private String getMessage(String id) {
        ResourceBundle rb =
                ResourceBundle.getBundle("SingleCharaMessage", new ResourceBundleUtf8Control());
        return rb.getString(id);
    }

    public void changeJobPane(Pane pane, NAVI navi) {
        // 一度描写を消す
        pane.getChildren().clear();

        // 再描画
        ImageAnimationView img = FullSizeAnimeManager.moveView(navi);
        img.play();
        pane.getChildren().add(img);
    }

    public void create(int playerNum) {
        PartyManager.setParty(createPlayer(playerNum), playerNum);

        // 描写用の配列に格納
        PaneAnimationManager.getInstance().setAnimationChara(playerNum);

        // 早すぎると格納が追い付かないので前ビューに戻る時間をちょっとだけ遅くする
        ThreadUtil.sleep(50L);
    }

    private Characters createPlayer(int playerNum) {
        JOB job = FullSizeAnimeManager.getCreationInfo().getJob();
        JOB_PARAM_CORRECTION info = job.getParam();
        charaBuilder.name(job.name()).job(job).type(EP_TYPE.PLAYER).id(playerNum)
                .hp(correctSts(info.getHp(), _Hp)).mhp(correctSts(info.getHp(), _Hp))
                .mp(correctSts(info.getMp(), _Mp)).mmp(correctSts(info.getMp(), _Mp))
                .strn(correctSts(info.getStrn(), _Strength))
                .intl(correctSts(info.getIntl(), _Intelligence))
                .vitl(correctSts(info.getVitl(), _Vitality))
                .sped(correctSts(info.getSped(), _Speed))
                .accr(correctSts(info.getAccr(), _Accuracy))
                .luck(correctSts(info.getLuck(), _Luck));
        return createPlayerBy(job);
    }


    private Characters createPlayerBy(JOB job) {
        switch (job) {
            case ARCHER_F:
                return charaBuilder.buildArcher();
            case CHIEF_M:
                return charaBuilder.buildChief();
            case PRIEST_F:
                return charaBuilder.buildPriest();
            case WARRIOR_F:
                return charaBuilder.buildWarrior();
            case WARRIOR_M:
                return charaBuilder.buildWarrior();
            case WITCH_F:
                return charaBuilder.buildWitch();
        }
        throw new IllegalArgumentException("createPlayerBy 入力ジョブが不正です:" + job);
    }

    private int correctSts(double correctVal, int param) {
        return (int) (_CorrectionBias * correctVal * param);
    }

    public synchronized void changeSts(STATUS sts, COMMAND cmd) {
        ChangeResult result = null;
        switch (sts) {
            case STR:
                result = changer.change(cmd, _Strength, _RemainPoint);
                _Strength = result.getStsNum();
                setTextAndRemain(result);
                break;
            case INT:
                result = changer.change(cmd, _Intelligence, _RemainPoint);
                _Intelligence = result.getStsNum();
                setTextAndRemain(result);
                break;
            case VIT:
                result = changer.change(cmd, _Vitality, _RemainPoint);
                _Vitality = result.getStsNum();
                setTextAndRemain(result);
                break;
            case SPD:
                result = changer.change(cmd, _Speed, _RemainPoint);
                _Speed = result.getStsNum();
                setTextAndRemain(result);
                break;
            case ACC:
                result = changer.change(cmd, _Accuracy, _RemainPoint);
                _Accuracy = result.getStsNum();
                setTextAndRemain(result);
                break;
            case LUK:
                result = changer.change(cmd, _Luck, _RemainPoint);
                _Luck = result.getStsNum();
                setTextAndRemain(result);
                break;
        }
    }

    // 同一処理でcase文で書きたくなかったので関数化する
    private void setTextAndRemain(ChangeResult result) {
        _RemainPoint = result.getRemainPoint();
        textLabelProperty.set(result.getChangeWord());
        remainProperty.set(String.valueOf(result.getRemainPoint()));
    }

    public double getSts(STATUS sts) {

        switch (sts) {
            case STR:
                return _Strength / 5.0;
            case INT:
                return _Intelligence / 5.0;
            case VIT:
                return _Vitality / 5.0;
            case SPD:
                return _Speed / 5.0;
            case ACC:
                return _Accuracy / 5.0;
            case LUK:
                return _Luck / 5.0;
        }
        throw new IllegalArgumentException("入力値不正 ステータス:" + sts);
    }

    public void setJobName() {
        String jobName = FullSizeAnimeManager.getCreationInfo().getJob().getJobName();
        jobNameProperty.set(jobName);
    }

    public void resetRemainPoint() {
        _RemainPoint = _InitiateRemainPoint;
        remainProperty.set(String.valueOf(_RemainPoint));
    }

    /**
     * 職パネルの映像とそれに付随するコメントを変更する.
     * <p>
     * 職毎のパラメータに用いるポイントもリセットする
     *
     * @param jobPane
     * @param left
     */
    public void execute(Pane jobPane, NAVI navi) {
        // 変更
        this.changeJobPane(jobPane, navi);
        this.changeTextLabel(navi);
        this.changeExplain();

        // リセット
        this.resetWholeStatus();
        this.resetRemainPoint();
    }
}
