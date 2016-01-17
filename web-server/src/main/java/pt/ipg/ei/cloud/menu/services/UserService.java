package pt.ipg.ei.cloud.menu.services;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import pt.ipg.ei.cloud.menu.server.auth.ServerUserData;
import pt.ipg.ei.cloud.menu.shared.model.UserApp;

@Api(version = "v1", name = "user")
public class UserService {

    @Inject
    @Named("me")
    private Provider<ServerUserData> me;

    @ApiMethod(httpMethod = ApiMethod.HttpMethod.GET, path = "userApp")
    public UserApp getUser() {
        return me.get();
    }


}
