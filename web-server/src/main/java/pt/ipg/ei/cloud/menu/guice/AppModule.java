package pt.ipg.ei.cloud.menu.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.servlet.RequestScoped;
import com.googlecode.objectify.ObjectifyFilter;
import pt.ipg.ei.cloud.menu.server.auth.*;
import pt.ipg.ei.cloud.menu.validation.BeanValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;

public class AppModule extends AbstractModule {

    private static boolean SIMULATE_USER = false;

    @Provides
    @Singleton
    private Validator getValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }

    @Provides
    @Singleton
    private BeanValidator beanValidator(Validator validator) {
        return new BeanValidator(validator);
    }


    @Provides
    @Named("me")
    @RequestScoped
    private ServerUserData mePerson(HttpServletRequest request) throws IOException {
        if (SIMULATE_USER) {
            return new SimulatedUser();
        }

        final Object displayName = request.getSession().getAttribute("displayName");
        if (displayName == null) {
            return new GuestUser(Utils.loginUrl(request));
        } else {
            return new SessionUser(request);
        }
    }


    @Override
    protected void configure() {
        bind(ObjectifyFilter.class).in(Singleton.class);
    }
}
