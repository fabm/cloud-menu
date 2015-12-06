package pt.ipg.ei.cloud.menu.guice;

import com.google.inject.servlet.ServletModule;
import pt.ipg.ei.cloud.menu.server.auth.Logout;
import pt.ipg.ei.cloud.menu.server.auth.MyAuthCallback;
import pt.ipg.ei.cloud.menu.server.auth.MyAuthEntry;


public class ServletModuleImp extends ServletModule{
    @Override
    protected void configureServlets() {
        serve("/logout").with(Logout.class);
        serve("/auth").with(MyAuthEntry.class);
        serve("/oauth2callback").with(MyAuthCallback.class);
    }
}
