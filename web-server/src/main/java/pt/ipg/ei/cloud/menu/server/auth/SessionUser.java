package pt.ipg.ei.cloud.menu.server.auth;

import javax.servlet.http.HttpServletRequest;

public class SessionUser extends ServerUserData{

    public static final String GOOGLE_ID="googleId";
    public static final String DISPLAY_NAME="displayName";

    private final String id;

    public SessionUser(HttpServletRequest request) {
        this.id = (String) request.getSession().getAttribute(GOOGLE_ID);
        this.displayName = (String) request.getSession().getAttribute(DISPLAY_NAME);
        this.authenticated = true;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }
}
