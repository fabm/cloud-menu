package pt.ipg.ei.cloud.menu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import org.fusesource.restygwt.client.Resource;
import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.constants.IconType;
import pt.ipg.ei.cloud.menu.client.panels.Panel1;
import pt.ipg.ei.cloud.menu.client.panels.Panel2;
import pt.ipg.ei.cloud.menu.client.panels.PanelChooser;
import pt.ipg.ei.cloud.menu.client.storage.StorageEvent;
import pt.ipg.ei.cloud.menu.client.storage.StorageEventHandler;
import pt.ipg.ei.cloud.menu.client.storage.StorageEventUtils;
import pt.ipg.ei.cloud.menu.client.translations.Translations;

import java.util.Map;
import java.util.logging.Logger;

public class Application extends Composite {
    interface MyUiBinder extends UiBinder<Widget, Application> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    SimplePanel mainPanel;
    private StorageEventUtils storageEventUtils;
    @UiField
    Anchor authIcon;

    private Translations translations = GWT.create(Translations.class);

    public Application() {
        initWidget(uiBinder.createAndBindUi(this));
        mainPanel.add(new Panel1());
        updateAuthenticationIcon(isItConnected());
        History.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                mainPanel.setWidget(PanelChooser.instance().choose(event.getValue()));
            }
        });

        storageEventUtils = GWT.create(StorageEventUtils.class);

        Logger.getLogger("storage").info("add storage handler");
        storageEventUtils.createEventListener(new StorageEventHandler() {
            @Override
            public void fireStorageEvent(StorageEvent storageEvent) {
                if (storageEvent.key() != "oauthstate") {
                    return;
                }
                Logger.getLogger(Application.class.getName()).info("oauthstate - event:"+storageEvent.newValue());
                updateAuthenticationIcon(storageEvent.newValue() == "connected");
            }
        });
    }

    private boolean isItConnected() {
        return Storage.getLocalStorageIfSupported().getItem("oauthstate") == "connected";
    }

    private void updateAuthenticationIcon(boolean connected) {
        if (connected) {
            final String name = Storage.getLocalStorageIfSupported().getItem("name");
            Logger.getLogger(Application.class.getName()).info("oauth name:"+name);
            authIcon.setText(name);
            authIcon.setIcon(IconType.SIGN_OUT);
        } else {
            authIcon.setText(translations.authenticateWith());
            authIcon.setIcon(IconType.GOOGLE_PLUS);
        }
    }


    @UiHandler("authIcon")
    void onSignIn(ClickEvent clickEvent) {
        if (isItConnected()) {
            final Resource resource = new Resource(GWT.getHostPageBaseURL() + "logout");
            try {
                resource.get().send(new RequestCallback() {
                    @Override
                    public void onResponseReceived(Request request, Response response) {
                        Storage.getLocalStorageIfSupported().setItem("oauthstate","disconnected");
                        updateAuthenticationIcon(false);
                    }

                    @Override
                    public void onError(Request request, Throwable exception) {
                        Logger.getLogger("current call problem");
                    }
                });
            } catch (RequestException e) {
                Logger.getLogger("current call problem");
            }
        } else {
            Window.open("auth", "_blank", "toolbar=yes, scrollbars=yes, resizable=yes, width=600, height=800");
        }
    }

}