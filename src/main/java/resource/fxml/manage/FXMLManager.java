package resource.fxml.manage;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import resource.fxml.enums.FXMLenums;

public class FXMLManager {

    public static Parent getResource(Object obj) {
        try {
            URL uri = ClassLoader.getSystemResource(FXMLenums.getPath(obj));
            return FXMLLoader.load(uri);

        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new IllegalArgumentException();
    }
}
