package server.tests;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.HashMap;
import java.util.Map;

@Path("/hello")
public class HelloWebapp {

    @GET()
    public Map<String, String> hello() {
        Map<String,String> map = new HashMap<String, String>();
        map.put("result","ola");
        return map;
    }
}