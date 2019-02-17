package application.model.item;

import java.util.ResourceBundle;
import application.ctrl.ChoseItemController;
import application.model.node.tooltip.TooltipBehavior;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Duration;
import resource.fxml.manage.ResourceBundleUtf8Control;

public class ItemToolTipManager {

    private static final Duration _ApperTime = new Duration(10);
    private static final Duration _DisapperTime = new Duration(10);

    private static final TooltipBehavior bhv = new TooltipBehavior();
    private static final String _FamilyFont = "Arial Black";
    private static final Font _TipFont = Font.font(_FamilyFont);

    public void initToolTip() {
        for (Pane p : ChoseItemController.get_PaneList()) {
            installToolTip(bhv, p, getPropertiesFrom(p));
        }
    }

    private void installToolTip(TooltipBehavior bhv, Pane p, String word) {
        bhv.setOpenDuration(_ApperTime);
        bhv.setHideDuration(Duration.INDEFINITE);
        bhv.setLeftDuration(_DisapperTime);
        bhv.setDefautlTooltip(makeTooltip(word));
        bhv.install(p, bhv.getDefaultTooltip());
    }

    private Tooltip makeTooltip(String word) {
        Tooltip tooltip = new Tooltip(word);
        tooltip.setFont(_TipFont);
        return tooltip;
    }

    private String getPropertiesFrom(Pane p) {
        return getMessage(makeNumber(p));
    }

    private String getMessage(String id) {
        ResourceBundle rb =
                ResourceBundle.getBundle("conf/prop/itemExplain", new ResourceBundleUtf8Control());
        return rb.getString(id);
    }

    private String makeNumber(Pane p) {
        String tmp = p.getId().substring("itemPane".length());
        switch (tmp.length()) {
            case 1:
                return "00" + tmp;
            case 2:
                return "0" + tmp;
            case 3:
                return tmp;
            default:
                throw new IllegalArgumentException("入力値のIDが異常値" + p);
        }
    }
}
