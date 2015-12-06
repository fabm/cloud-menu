package pt.ipg.ei.cloud.menu.client.panels;

import com.google.gwt.user.client.ui.Widget;

public class PanelChooser {
    private static PanelChooser panelChooser;

    public static PanelChooser instance() {
        if (panelChooser == null) {
            panelChooser = new PanelChooser();
        }
        return panelChooser;
    }

    public Widget choose(String token){
        if(token.equals("buttons")){
            return new Panel1();
        }
        if(token.equals("other")){
            return new Panel2();
        }
        return new IntroPanel();
    }

}
