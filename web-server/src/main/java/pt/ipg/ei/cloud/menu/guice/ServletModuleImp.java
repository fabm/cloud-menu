package pt.ipg.ei.cloud.menu.guice;

import com.google.inject.servlet.ServletModule;
import com.googlecode.objectify.ObjectifyFilter;
import com.googlecode.objectify.ObjectifyService;
import pt.ipg.ei.cloud.menu.entities.History;
import pt.ipg.ei.cloud.menu.entities.RestaurantEntity;
import pt.ipg.ei.cloud.menu.server.auth.Logout;
import pt.ipg.ei.cloud.menu.server.auth.MyAuthCallback;
import pt.ipg.ei.cloud.menu.server.auth.MyAuthEntry;
import pt.ipg.ei.cloud.menu.servlets.ImageServlet;
import pt.ipg.ei.cloud.menu.servlets.UploadServlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static pt.ipg.ei.cloud.menu.guice.ServletAction.create;


public class ServletModuleImp extends ServletModule {

    @Override
    protected void configureServlets() {
        registerEntities();
        serve("/logout").with(Logout.class);
        serve("/auth").with(MyAuthEntry.class);
        serve("/oauth2callback").with(MyAuthCallback.class);
        serve("/upload").with(UploadServlet.class);
        serve("/image").with(ImageServlet.class);
        filter("/*").through(ObjectifyFilter.class);

        filter("/*").through(new Filter() {
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                create((HttpServletRequest)request,(HttpServletResponse)response);
                chain.doFilter(request,response);
            }

            @Override
            public void init(FilterConfig filterConfig) throws ServletException {

            }

            @Override
            public void destroy() {

            }
        });

    }

    private void registerEntities() {
        ObjectifyService.register(RestaurantEntity.class);
        ObjectifyService.register(History.class);
    }
}
