package pt.ipg.ei.cloud.menu.server.auth;

import com.google.appengine.repackaged.org.codehaus.jackson.map.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static pt.ipg.ei.cloud.menu.shared.Constants.OAUTH_CONNECTED_STATE_DISCONNECTED;

@Singleton
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),new GuestUser(Utils.loginUrl(req)));
    }
}
