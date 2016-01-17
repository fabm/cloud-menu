package pt.ipg.ei.cloud.menu.client.panels;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.NavbarNav;
import pt.ipg.ei.cloud.menu.client.panels.menu.HistoryTokens;
import pt.ipg.ei.cloud.menu.client.translations.Translations;

public class PanelChooser {
    private static PanelChooser panelChooser;

    public static PanelChooser instance() {
        if (panelChooser == null) {
            panelChooser = new PanelChooser();
        }
        return panelChooser;
    }

    private NavbarNav menuBar;

    public void setMenuBar(NavbarNav menuBar) {
        this.menuBar = menuBar;
        initMenuBar();
    }

    private void initMenuBar(){
        Translations translations = GWT.create(Translations.class);
        AnchorListItem listItem = new AnchorListItem(translations.restaurantMenuRegister());
        listItem.setTargetHistoryToken(HistoryTokens.restaurantRegister);
        menuBar.add(listItem);
    }

    public Widget choose(String token) {
        if("res-reg".equals(token)){
            return new RestaurantRegisterPanel();
        }else if("test".equals(token)){
            return new Panel2();
        }
        return new IntroPanel();
    }

}
