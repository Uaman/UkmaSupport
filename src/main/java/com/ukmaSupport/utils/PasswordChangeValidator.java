package com.ukmaSupport.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component
public class PasswordChangeValidator {
    public void validate(String pass,String confPass, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "valid.password", "Password is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confPassword", "valid.confPassword", "Confirm password is required.");

        if (!pass.equals(confPass))
            errors.rejectValue("confPassword", "valid.confPasswordDiff", "Passwords are different.");
    }

    }
