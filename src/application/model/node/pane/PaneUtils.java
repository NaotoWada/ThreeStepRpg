package application.model.node.pane;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;

public class PaneUtils {

    public static int getId(Pane pane) {
        String paneId = pane.getId();
        String id = paneId.substring(paneId.length() - 1);
        return Integer.parseInt(id) - 1;
    }

    public static int getId(CheckBox cBox) {
        String paneId = cBox.getId();
        String[] split = paneId.split("checkItem");
        String id = split[1];
        return Integer.parseInt(id);
    }
}
