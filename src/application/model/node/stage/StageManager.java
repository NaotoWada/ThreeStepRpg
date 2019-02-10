package application.model.node.stage;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageManager {

    private static Stage _PresentStage;

    public synchronized static void change(Scene in) {
        _PresentStage.setScene(in);
    }

    public synchronized static void show() {
        _PresentStage.show();
    }

    public synchronized static void changeThenShow(Scene in) {
        _PresentStage.setScene(in);
        _PresentStage.show();
    }

    public synchronized static void setStageIfNull(Stage in) {
        if (_PresentStage == null) {
            _PresentStage = in;
        }
    }

    public static void setTitle(String in) {
        _PresentStage.setTitle(in);
    }
}
