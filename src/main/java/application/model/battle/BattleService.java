package application.model.battle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import consts.PartyConsts;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BattleService extends Service<Void> {

    private StringProperty textStairsProperty;
    private String stairsText;
    private StringProperty textTurnProperty;
    private String turnText;
    private StringProperty textRankProperty;
    private String rankText;
    private StringProperty textAreaProperty;
    private String areaText;

    private BattleService() {
        stairsText = "1";
        textStairsProperty = new SimpleStringProperty(stairsText);
        turnText = "1";
        textTurnProperty = new SimpleStringProperty(turnText);
        rankText = "0";
        textRankProperty = new SimpleStringProperty(rankText);
        areaText = "";
        textAreaProperty = new SimpleStringProperty(areaText);
    }

    private static final int _MaxDigitDamage = 9;
    @Getter
    @Setter
    private static ProgressBar _HpBarChara1;

    @Getter
    @Setter
    private static ProgressBar _HpBarChara2;

    @Getter
    @Setter
    private static ProgressBar _HpBarChara3;

    @Getter
    @Setter
    private static ProgressBar _HpBarMonster;

    @Getter
    @Setter
    private static ProgressBar _MpBarChara1;

    @Getter
    @Setter
    private static ProgressBar _MpBarChara2;

    @Getter
    @Setter
    private static ProgressBar _MpBarChara3;

    @Getter
    private static final BattleService instance = new BattleService();

    @Getter
    protected static Pane[] _CharaBattlePanes = new Pane[PartyConsts._LimitOfMember];
    @Getter
    @Setter
    protected static Pane _MonsBattlePane;
    @Getter
    protected static Pane[] _Chara1DamagePane = new Pane[_MaxDigitDamage];
    @Getter
    protected static Pane[] _Chara2DamagePane = new Pane[_MaxDigitDamage];
    @Getter
    protected static Pane[] _Chara3DamagePane = new Pane[_MaxDigitDamage];
    @Getter
    protected static Pane[] _MonsterDamagePane = new Pane[_MaxDigitDamage];

    private static ExecutorService exec = Executors.newFixedThreadPool(1);
    private static Task<Void> task;

    @Override
    protected Task<Void> createTask() {
        return new BattleActionTask();
    }

    public synchronized void start() {
        exec = Executors.newFixedThreadPool(1);
        task = createTask();
        exec.execute(task);
    }

    public void stop() {
        task.cancel();
        exec.shutdownNow();
    }

    public void allocatePlayerPane(Pane paneChara1, Pane paneChara2, Pane paneChara3) {
        _CharaBattlePanes[0] = paneChara1;
        _CharaBattlePanes[1] = paneChara2;
        _CharaBattlePanes[2] = paneChara3;
    }

    public void allocateNumPaneChara(Pane... panes) {
        // ソートしてある事を前提に使用する事
        int firstCharaLimit = 9;
        int secondCharaLimit = 18;

        // 1キャラ目
        for (int i = 0; i < _MaxDigitDamage; i++) {
            _Chara1DamagePane[i] = panes[i];
        }

        // 2キャラ目
        for (int i = 0; i < _MaxDigitDamage; i++) {
            int paneSecond = i + firstCharaLimit;
            _Chara2DamagePane[i] = panes[paneSecond];
        }

        // 3キャラ目
        for (int i = 0; i < _MaxDigitDamage; i++) {
            int paneThird = i + secondCharaLimit;
            _Chara3DamagePane[i] = panes[paneThird];
        }
    }

    public void allocateNumPaneMonster(Pane... panes) {

        for (int i = 0; i < _MaxDigitDamage; i++) {
            _MonsterDamagePane[i] = panes[i];
        }
    }

    public StringProperty textStairsProperty() {
        return textStairsProperty;
    }

    public StringProperty textTurnProperty() {
        return textTurnProperty;
    }

    public StringProperty textRankProperty() {
        return textRankProperty;
    }

    public StringProperty textAreaProperty() {
        return textAreaProperty;
    }

}
