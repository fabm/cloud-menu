package pt.ipg.ei.cloud.menu.guice;

import com.google.api.server.spi.guice.GuiceSystemServiceServletModule;

import java.util.HashSet;
import java.util.Set;

public class GuiceSSSModule extends GuiceSystemServiceServletModule {

    @Override
    protected void configureServlets() {
        super.configureServlets();

        Set<Class<?>> serviceClasses = new HashSet<Class<?>>();
        //add cloudEndpoints classes
        //serviceClasses.add(MessagingEndpoint.class);
        this.serveGuiceSystemServiceServlet("/_ah/spi/*", serviceClasses);
    }
}