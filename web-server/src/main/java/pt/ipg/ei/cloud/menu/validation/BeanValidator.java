package pt.ipg.ei.cloud.menu.validation;

import javax.validation.Validator;

public class BeanValidator {
    private Validator validator;

    public BeanValidator(Validator validator) {
        this.validator = validator;
    }

    public <T> BeanValidation<T> getValidation(T bean){
        return new BeanValidation<T>(validator,bean);
    }
}
