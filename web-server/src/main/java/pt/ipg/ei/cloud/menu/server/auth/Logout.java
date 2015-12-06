package pt.ipg.ei.cloud.menu.server.auth;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class Logout extends HttpServlet {

    @Inject
    private Provider<AuthCookies> authCookiesProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authCookiesProvider.get().delete();
        Utils.configFreemarker("disconnected",resp.getWriter());
    }
}
