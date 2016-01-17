package pt.ipg.ei.cloud.menu.guice;

import com.google.api.server.spi.guice.GuiceSystemServiceServletModule;
import pt.ipg.ei.cloud.menu.services.UserService;
import pt.ipg.ei.cloud.menu.services.RestaurantService;

import java.util.HashSet;
import java.util.Set;

public class GuiceSSSModule extends GuiceSystemServiceServletModule {


    public static final String AH_SPI = "/_ah/spi/*";

    @Override
    protected void configureServlets() {

        super.configureServlets();

        Set<Class<?>> serviceClasses = new HashSet<Class<?>>();
        //add cloudEndpoints classes
        serviceClasses.add(UserService.class);
        serviceClasses.add(RestaurantService.class);
        this.serveGuiceSystemServiceServlet(AH_SPI, serviceClasses);


    }


}