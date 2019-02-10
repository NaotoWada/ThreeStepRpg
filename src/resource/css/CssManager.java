package resource.css;

import javafx.scene.Scene;

public class CssManager {

    public static void addStyleSheet(Scene scene, String file) {
        String externalForm = ClassLoader.getSystemResource(file).toExternalForm();
        scene.getStylesheets().add(externalForm);
    }
}
