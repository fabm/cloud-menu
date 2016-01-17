package pt.ipg.ei.cloud.menu.validation;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BeanValidation<T> {

    private Validator validator;
    private T bean;
    private Set<ConstraintViolation<T>> constraints;


    public BeanValidation(Validator validator, T bean) {
        this.validator = validator;
        this.bean = bean;
        evaluateErrors();
    }

    private BeanValidation<T> evaluateErrors(){
        constraints = validator.validate(bean);
        return this;
    }

    public Set<ConstraintViolation<T>> getConstraints() {
        return constraints;
    }

    public boolean isValid(){
        return constraints.isEmpty();
    }

    public List<String> getErrors(){
        List<String> errors = new ArrayList<String>();
        for(ConstraintViolation<T> error:constraints){
            errors.add(error.getPropertyPath()+":"+error.getMessage());
        }
        return errors;
    }

    public T getBean(){
        return bean;
    }


}
