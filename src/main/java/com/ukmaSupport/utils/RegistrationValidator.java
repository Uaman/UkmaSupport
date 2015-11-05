package com.ukmaSupport.utils;

import org.springframework.validation.BindingResult;

import com.ukmaSupport.POJO.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RegistrationValidator implements Validator {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public boolean supports(Class<?> paramClass) {
        return User.class.equals(paramClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }

    public void validate(Object obj, BindingResult errors) {
        User form = (User) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "valid.firstName", "First name is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "valid.lastName", "Last name is required.");
        if (!form.getEmail().matches(EMAIL_PATTERN)) {
            errors.rejectValue("email", "valid.email", "Email is required.");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "valid.password", "Password is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confPassword", "valid.confPassword", "Confirm password is required.");
        if (!form.getPassword().equals(form.getConfPassword())) {
            errors.rejectValue("confPassword", "valid.confPasswordDiff", "Passwords are different.");
        }
    }
}