package pt.ipg.ei.cloud.menu.server.auth;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.model.Person;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import pt.ipg.ei.cloud.menu.guice.ServletAction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static pt.ipg.ei.cloud.menu.server.auth.Utils.configTemplate;
import static pt.ipg.ei.cloud.menu.shared.Constants.OAUTH_CONNECTED_STATE_CONNECTED;

@Singleton
public class MyAuthCallback extends HttpServlet {
    private final Lock lock = new ReentrantLock();
    private AuthorizationCodeFlow flow;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletAction.create(req,resp);
        StringBuffer buf = req.getRequestURL();
        if (req.getQueryString() != null) {
            buf.append('?').append(req.getQueryString());
        }
        AuthorizationCodeResponseUrl responseUrl = new AuthorizationCodeResponseUrl(buf.toString());
        String code = responseUrl.getCode();
        if (responseUrl.getError() != null) {
            onError(req, resp, responseUrl);
        } else if (code == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print("Missing authorization code");
        } else {
            lock.lock();
            try {
                if (flow == null) {
                    flow = initializeFlow();
                }
                String redirectUri = getRedirectUri(req);

                TokenResponse response = flow.newTokenRequest(code)
                        .setRedirectUri(redirectUri)
                        .execute();

                ServerUserData plusUser = new PlusUser(response.getAccessToken());

                req.getSession(true).setAttribute(SessionUser.GOOGLE_ID,plusUser.getId());
                req.getSession().setAttribute(SessionUser.DISPLAY_NAME,plusUser.getDisplayName());

                resp.sendRedirect("/");
            } finally {
                lock.unlock();
            }
        }
    }

    private void onSuccess(HttpServletResponse resp) throws IOException {
        configTemplate(OAUTH_CONNECTED_STATE_CONNECTED, resp.getWriter());
    }

    private String getRedirectUri(HttpServletRequest req) {
        return Utils.getRedirectUri(req);
    }

    private AuthorizationCodeFlow initializeFlow() throws IOException {
        return Utils.initializeFlow();
    }

    private void onError(HttpServletRequest req, HttpServletResponse resp, AuthorizationCodeResponseUrl responseUrl) throws IOException {
        configTemplate("disconnected", resp.getWriter());
    }
}
