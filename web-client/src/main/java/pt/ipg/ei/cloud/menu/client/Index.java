package pt.ipg.ei.cloud.menu.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class Index implements EntryPoint{

    @Override
    public void onModuleLoad() {
        SimplePanel simplePanel = new SimplePanel();
        simplePanel.setWidget(new Application());
        RootPanel.get().add(simplePanel);
    }
}
