package pt.ipg.ei.cloud.menu.client;

import pt.ipg.ei.cloud.menu.server.auth.ServerUserData;

public class ServerUserDataTest extends ServerUserData{

    @Override
    public String getId() {
        return "123";
    }
}
