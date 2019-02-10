package service;

import application.ctrl.TopMenuController;
import application.model.node.stage.StageManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppService extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // 窓変更不可と、タイトルの設定
            primaryStage.setResizable(false);
            StageManager.setStageIfNull(primaryStage);
            StageManager.setTitle("3 STEP RPG");

            // シーン変更
            TopMenuController.getInstance().changeThenShow();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
