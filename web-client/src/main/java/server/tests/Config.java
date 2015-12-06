package server.tests;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.webapp.WebAppContext;

public class Config {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/");
        webAppContext.setWar("/Users/francisco/git/ipg/cloud-menu/web-client/src/main/webapp");
        server.setHandler(webAppContext);

        server.start();
        server.join();

    }
}
