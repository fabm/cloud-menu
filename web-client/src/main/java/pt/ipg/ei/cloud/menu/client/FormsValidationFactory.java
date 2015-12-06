package pt.ipg.ei.cloud.menu.client;

import pt.ipg.ei.cloud.menu.client.model.MyObject;

import javax.validation.Validator;


import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;

public class FormsValidationFactory extends AbstractGwtValidatorFactory {



    @GwtValidation(MyObject.class)
    public interface GwtValidator extends Validator {
    }

    @Override
    public AbstractGwtValidator createValidator() {
        return GWT.create(GwtValidator.class);
    }

}