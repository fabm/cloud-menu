package pt.ipg.ei.cloud.menu.server.auth;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.model.Person;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static pt.ipg.ei.cloud.menu.server.auth.Utils.configFreemarker;

@Singleton
public class MyAuthCallback extends HttpServlet {
    private final Lock lock = new ReentrantLock();
    @Inject
    private Provider<AuthCookies> authCookiesProvider;
    private AuthorizationCodeFlow flow;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

                GoogleCredential googleCredential = new GoogleCredential().setAccessToken(response.getAccessToken());

                Plus plus = new Plus.Builder(Utils.HTTP_TRANSPORT, Utils.JSON_FACTORY, googleCredential).setApplicationName("").build();
                final Person person = plus.people().get("me").execute();


                authCookiesProvider.get().save(person.getId(), response.getAccessToken());
                System.out.println("nick:"+person.getDisplayName());
                onSuccess(person.getDisplayName(),resp);
            } finally {
                lock.unlock();
            }
        }
    }

    private void onSuccess(String nickname, HttpServletResponse resp) throws IOException {
        configFreemarker(nickname,"connected", resp.getWriter());
    }

    private String getRedirectUri(HttpServletRequest req) {
        return Utils.getRedirectUri(req);
    }

    private AuthorizationCodeFlow initializeFlow() throws IOException {
        return Utils.initializeFlow();
    }

    private void onError(HttpServletRequest req, HttpServletResponse resp, AuthorizationCodeResponseUrl responseUrl) throws IOException {
        configFreemarker("disconnected", resp.getWriter());
    }
}
