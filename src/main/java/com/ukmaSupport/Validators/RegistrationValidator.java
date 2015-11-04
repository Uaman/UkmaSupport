package com.ukmaSupport.Validators;
import java.util.List;

import com.ukmaSupport.POJO.models.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Roma on 03.11.2015.
 */

public class RegistrationValidator implements Validator {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public boolean supports(Class<?> paramClass) {
        return User.class.equals(paramClass);
    }

    public void validate(Object obj, Errors errors) {
        User form = (User) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "valid.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "valid.lastName");
        if(!form.getEmail().matches(EMAIL_PATTERN)) {
            errors.rejectValue("email","valid.email");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "valid.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confPassword", "valid.confPassword");
        if (!form.getPassword().equals(form.getConfPassword())) {
            errors.rejectValue("confPassword", "valid.confPasswordDiff");
        }
    }
}