package server;

        import org.eclipse.jetty.server.Server;
        import org.eclipse.jetty.servlet.ServletContextHandler;
        import org.eclipse.jetty.servlet.ServletHolder;
        import org.eclipse.jetty.webapp.WebAppContext;
        import services.Calculator;
        import services.HelloWebapp;


public class RestServer {

    private static final Class<?>[] serviceClasses = new Class[]{
            HelloWebapp.class, Calculator.class
    };

    public static void main(String[] args) throws Exception {
        ServletContextHandler context = getWAC(args[0]);

        Server jettyServer = new Server(8080);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/_ah/spi/*");
        jerseyServlet.setInitOrder(0);

        StringBuilder sb = new StringBuilder();

        int i=0;
        for (; i < serviceClasses.length-1; i++) {
            sb.append(serviceClasses[i].getCanonicalName());
            sb.append(", ");
        }
        sb.append(serviceClasses[i].getCanonicalName());

        System.out.println(sb.toString());

        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.classnames",
                sb.toString());

        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }
    }

    private static ServletContextHandler getWAC(String war) {
        System.out.println(war);
        WebAppContext context = new WebAppContext();
        context.setWar(war);
        context.setContextPath("/");
        return context;
    }
}