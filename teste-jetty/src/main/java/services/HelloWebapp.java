package services;


import pt.ipg.ei.cloud.menu.client.model.MyObject;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/test")
public class HelloWebapp {

    @GET
    public MyObject hello() {
        MyObject myObject = new MyObject();
        myObject.setTbName("teste-object");
        return myObject;
    }

    @POST
    @Path("xpto")
    public MyObject post(MyObject myObject) {
        System.out.println("recieve: "+myObject.getTbName());
        MyObject mo = new MyObject();
        mo.setTbName("after modify");
        return mo;
    }

}