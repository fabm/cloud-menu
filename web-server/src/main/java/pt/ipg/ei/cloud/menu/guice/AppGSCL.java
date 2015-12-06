package pt.ipg.ei.cloud.menu.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class AppGSCL extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        System.out.println("creating injector");
        return Guice.createInjector(new ServletModuleImp(),new GuiceSSSModule(),new AppModule());
    }
}