package pt.ipg.ei.cloud.menu.server.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.model.Person;

import java.io.IOException;

public class PlusUser extends ServerUserData{

    private final String displayName;
    private final String id;
    private boolean authenticated;

    public PlusUser(String token) {
        Plus plus = new Plus.Builder(Utils.HTTP_TRANSPORT, Utils.JSON_FACTORY, new GoogleCredential().setAccessToken(token))
                .setApplicationName("cloud-menu")
                .build();
        try {
            Person service = plus.people().get("me").execute();
            this.displayName = service.getDisplayName();
            this.id = service.getId();
            authenticated = true;
        } catch (IOException e) {
            authenticated = false;
            throw new IllegalStateException(e);
        }

    }

    @Override
    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }
}
