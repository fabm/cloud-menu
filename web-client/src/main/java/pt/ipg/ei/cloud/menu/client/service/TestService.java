package pt.ipg.ei.cloud.menu.client.service;

import com.google.gwt.json.client.JSONValue;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import pt.ipg.ei.cloud.menu.client.model.MyObject;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

public interface TestService extends RestService{
    @GET
    @Path("test")
    void myServiceGet(MethodCallback<MyObject> cb);

    @POST
    @Path("test/xpto")
    void myServicePost(MyObject myObject, MethodCallback<MyObject> cb);
}
