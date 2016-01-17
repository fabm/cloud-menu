package pt.ipg.ei.cloud.menu.server.auth;

public class GuestUser extends ServerUserData{

    public GuestUser(String urlAuth) {
        this.urlAuth = urlAuth;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

}
