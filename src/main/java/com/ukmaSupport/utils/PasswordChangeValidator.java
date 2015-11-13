package com.ukmaSupport.utils;


import com.ukmaSupport.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import com.ukmaSupport.utils.PasswordEncryptor;

@Component
public class PasswordChangeValidator {
    @Autowired
    private UserService userDao;
    public void validate(String oldPass, String pass,String confPass, String email, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "valid.password", "Password is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confPassword", "valid.confPassword", "Confirm password is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oldPassword", "valid.oldPassword", "Old password is required.");

        if (!pass.equals(confPass))
            errors.rejectValue("confPassword", "valid.confPasswordDiff", "Passwords are different.");

        else if (!PasswordEncryptor.matches(oldPass, userDao.getByEmail(email).getPassword()))
            errors.rejectValue("oldPassword", "valid.oldPassword", "Incorrect old password.");
    }

    }
