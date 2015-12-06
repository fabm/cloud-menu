package pt.ipg.ei.cloud.menu.server.auth;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.inject.Inject;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class AuthCookies {
    private static final String GOOGLE_ID = "googleId";
    private static final String TOKEN = "Authorization";
    private String id;
    private String token;

    @Inject
    private HttpServletResponse response;
    @Inject
    private HttpServletRequest request;


    public void delete() {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return;
        }
        List<String> names = asList(GOOGLE_ID, TOKEN);

        for (Cookie cookie : cookies) {
            if (names.contains(cookie.getName())) {
                System.out.println("try to remove " + cookie.getName());
                cookie.setMaxAge(0);
                cookie.setValue("");
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }

    }

    public void loadCookies() {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            System.out.println("cookies are empty");
            return;
        }
        List<String> names = new ArrayList<String>(2);
        names.add(GOOGLE_ID);
        names.add(TOKEN);

        for (Cookie cookie : cookies) {
            if (names.contains(cookie.getName())) {
                set(cookie.getName(), cookie.getValue());
                names.remove(cookie.getName());
                if (names.isEmpty()) {
                    break;
                }
            }
        }
    }

    private void set(String name, String value) {
        if(name.equals(GOOGLE_ID)){
            this.id = value;
        }else if(name.equals(TOKEN)){
            this.token = value;
        }
    }

    public Credential getCredential(){
        return new GoogleCredential().setAccessToken(this.token);
    }

    public void save(String id, String token) {
        final Cookie cookieId = new Cookie(GOOGLE_ID, id);
        cookieId.setMaxAge(3600);
        response.addCookie(cookieId);
        final Cookie cookieToken = new Cookie(TOKEN, token);
        cookieToken.setMaxAge(3600);
        response.addCookie(cookieToken);
    }

    public boolean hasId(){
        return id == null;
    }



}
