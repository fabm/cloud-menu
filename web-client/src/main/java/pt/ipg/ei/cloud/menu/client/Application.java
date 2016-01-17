package pt.ipg.ei.cloud.menu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import org.fusesource.restygwt.client.*;
import org.gwtbootstrap3.client.ui.*;
import org.gwtbootstrap3.client.ui.constants.IconPosition;
import org.gwtbootstrap3.client.ui.constants.IconType;
import pt.ipg.ei.cloud.menu.client.panels.IntroPanel;
import pt.ipg.ei.cloud.menu.client.panels.PanelChooser;
import pt.ipg.ei.cloud.menu.client.panels.menu.NavbarFiller;
import pt.ipg.ei.cloud.menu.client.service.UserService;
import pt.ipg.ei.cloud.menu.client.storage.StorageEvent;
import pt.ipg.ei.cloud.menu.client.storage.StorageEventHandler;
import pt.ipg.ei.cloud.menu.client.storage.StorageEventUtils;
import pt.ipg.ei.cloud.menu.client.translations.MapTranslations;
import pt.ipg.ei.cloud.menu.client.translations.Translations;
import pt.ipg.ei.cloud.menu.shared.Constants;
import pt.ipg.ei.cloud.menu.shared.model.UserApp;
import pt.ipg.ei.cloud.menu.shared.model.profile.Profile;

import java.util.logging.Logger;

import static pt.ipg.ei.cloud.menu.shared.Constants.OAUTH_CONNECTED_STATE_CONNECTED;
import static pt.ipg.ei.cloud.menu.shared.Constants.OAUTH_STATE_KEY;

public class Application extends Composite {
    interface AppUiBinder extends UiBinder<Widget, Application> {
    }

    private static AppUiBinder uiBinder = GWT.create(AppUiBinder.class);


    @UiField
    SimplePanel mainPanel;
    @UiField
    NavbarNav menuBar;
    @UiField
    NavbarText rtNavBT;

    private NavbarFiller navbarFiller;
    private MapTranslations mapTranslations = GWT.create(MapTranslations.class);
    private Translations translations = GWT.create(Translations.class);

    public Application() {
        initWidget(uiBinder.createAndBindUi(this));

        navbarFiller = new NavbarFiller(menuBar);
        mainPanel.add(new IntroPanel());

        UserService userService = GWT.create(UserService.class);


        final Resource resource = new Resource(GWT.getHostPageBaseURL() + "_ah/api/user/v1");
        RestServiceProxy rsp = (RestServiceProxy) userService;
        rsp.setResource(resource);

        userService.getUser(new MethodCallback<UserApp>() {
            @Override
            public void onFailure(Method method, Throwable exception) {

            }

            @Override
            public void onSuccess(Method method, UserApp response) {
                updateAuthenticationStatus(response);
            }
        });


        changePanel(History.getToken());
        History.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                changePanel(event.getValue());
            }
        });

    }

    private void changePanel(String token) {
        mainPanel.setWidget(PanelChooser.instance().choose(token));
    }

    private void applyProfiles(UserApp response) {
        rtNavBT.clear();
        FormLabel formLabel = new FormLabel();
        formLabel.addStyleName("inlineDisplay");
        formLabel.setText(translations.navbarProfile());
        rtNavBT.add(formLabel);
        final ListBox profileLB = new ListBox();
        profileLB.addStyleName("listBoxNavBar");
        profileLB.getElement().getStyle().setProperty("width", "auto");
        profileLB.getElement().getStyle().setProperty("display", "inline-block");
        profileLB.clear();
        for (Profile profile : Profile.values()) {
            profileLB.addItem(mapTranslations.getProfile(profile), profile.name());
        }
        rtNavBT.add(profileLB);
        Anchor authIcon = new Anchor();
        authIcon.setText(response.getDisplayName());
        authIcon.setIcon(IconType.SIGN_OUT);
        authIcon.setIconPosition(IconPosition.RIGHT);
        authIcon.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                onSignIn(event, true, null);
            }
        });
        rtNavBT.add(authIcon);

    }

    private void updateAuthenticationStatus(UserApp userApp) {
        if (userApp != null && userApp.isAuthenticated()) {
            applyProfiles(userApp);
        } else {
            Window.Location.assign("#");
            fillRtNavBt(userApp.getUrlAuth());
        }
    }

    private void fillRtNavBt(final String loginUrl) {
        rtNavBT.clear();
        navbarFiller.clean();
        Anchor authIcon = new Anchor();
        authIcon.setText(translations.authenticateWith());
        authIcon.setIcon(IconType.GOOGLE_PLUS);
        authIcon.setIconPosition(IconPosition.RIGHT);
        authIcon.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                onSignIn(event, false, loginUrl);
            }
        });
        rtNavBT.add(authIcon);
    }

    private void onSignIn(ClickEvent clickEvent, boolean authenticated, String loginUrl) {
        if (authenticated) {
            final Resource resource = new Resource(GWT.getHostPageBaseURL() + "logout");
            try {
                resource.get().send(new RequestCallback() {
                    @Override
                    public void onResponseReceived(Request request, Response response) {
                        updateAuthenticationStatus(null);
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
            Window.Location.assign(loginUrl);
        }
    }

}