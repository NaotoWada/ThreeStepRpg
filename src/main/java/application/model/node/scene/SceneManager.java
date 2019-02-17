package application.model.node.scene;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class SceneManager {

    private static final int _Width = 920;
    private static final int _Height = 570;

    public static Scene newScene(Parent root) {
        return new Scene(root, _Width, _Height);
    }

}
