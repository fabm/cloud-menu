package pt.ipg.ei.cloud.menu.server.auth;

import java.io.IOException;
import java.util.Properties;

public class SimulatedUser extends ServerUserData {
    final Properties properties = new Properties();

    public SimulatedUser() {
        super();

        try {
            properties.load(getClass().getResourceAsStream("/authMock.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getId() {
        return properties.getProperty("googleId");
    }

    @Override
    public String getDisplayName() {
        return properties.getProperty("displayName");
    }

    @Override
    public boolean isAuthenticated() {
        return Boolean.parseBoolean(properties.getProperty("authenticated"));
    }

    @Override
    protected int getProfileSet() {
        return Integer.parseInt(properties.getProperty("profileset"),2);
    }
}
