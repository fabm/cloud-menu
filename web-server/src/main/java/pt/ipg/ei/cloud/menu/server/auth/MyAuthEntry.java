package pt.ipg.ei.cloud.menu.server.auth;

import com.google.appengine.repackaged.org.codehaus.jackson.map.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class MyAuthEntry extends HttpServlet {

    @Inject
    @Named("me")
    private Provider<ServerUserData> me;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),me.get());
    }
}
