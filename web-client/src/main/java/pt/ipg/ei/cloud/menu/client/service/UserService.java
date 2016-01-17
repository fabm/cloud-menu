package pt.ipg.ei.cloud.menu.client.service;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import pt.ipg.ei.cloud.menu.shared.model.UserApp;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

public interface UserService extends RestService{
    @GET
    @Path("userApp")
    void getUser(MethodCallback<UserApp> callback);

}
