package pt.ipg.ei.cloud.menu.server.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pt.ipg.ei.cloud.menu.shared.model.UserApp;

public abstract class ServerUserData extends UserApp{
    @JsonIgnore
    public abstract String getId();
}
