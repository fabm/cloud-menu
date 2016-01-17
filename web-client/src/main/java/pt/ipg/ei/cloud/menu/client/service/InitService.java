package pt.ipg.ei.cloud.menu.client.service;

import com.google.gwt.core.client.GWT;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;

public class InitService {
    public static RestServiceProxy init(String api,Object service){
        final Resource resource = new Resource(GWT.getHostPageBaseURL()+"_ah/api/"+api+"/");
        RestServiceProxy proxy = ((RestServiceProxy) service);
        ((RestServiceProxy)service).setResource(resource);
        return proxy;
    }
}
