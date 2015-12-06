package pt.ipg.ei.cloud.menu.server.auth;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.plus.Plus;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static pt.ipg.ei.cloud.menu.server.auth.Utils.loginUrl;

@Singleton
public class MyAuthEntry extends HttpServlet {

    @Inject
    private Provider<AuthCookies> authCookiesProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AuthCookies authCookies = authCookiesProvider.get();
        authCookies.loadCookies();

        String location;
        if (authCookies.hasId()) {
            resp.sendRedirect(loginUrl(req, true));
            return;
        }

        Plus plus = new Plus.Builder(Utils.HTTP_TRANSPORT, Utils.JSON_FACTORY, authCookies.getCredential())
                .setApplicationName("")
                .build();

        try {
            plus.people().get("me").execute();
            location = loginUrl(req, false);
        } catch (GoogleJsonResponseException e) {
            location = loginUrl(req, true);
        }
        resp.sendRedirect(location);
    }
}
