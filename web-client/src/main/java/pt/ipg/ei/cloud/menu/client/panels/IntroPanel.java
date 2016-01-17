package pt.ipg.ei.cloud.menu.client.panels;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class IntroPanel extends Composite {

    interface MyUiBinder extends UiBinder<Widget, IntroPanel> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    public IntroPanel() {
        initWidget(uiBinder.createAndBindUi(this));
    }

}
