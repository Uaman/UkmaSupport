package com.ukmaSupport.utils;


import com.ukmaSupport.models.EditForm;
import com.ukmaSupport.models.User;
import com.ukmaSupport.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import com.ukmaSupport.utils.PasswordEncryptor;
import org.springframework.validation.Validator;

@Component
public class PasswordChangeValidator implements Validator {
    @Autowired
    private UserService userDao;

    public boolean supports(Class<?> paramClass) {
        return EditForm.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        EditForm form = (EditForm) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "valid.password", "Password is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confPassword", "valid.confPassword", "Confirm password is required.");
        if (!PasswordEncryptor.matches(form.getOldPassword(), userDao.getByEmail(form.getEmail()).getPassword()))
            errors.rejectValue("oldPassword", "valid.oldPassword", "Incorrect old password.");
        else if (!form.getPassword().equals(form.getConfPassword()))
            errors.rejectValue("confPassword", "valid.confPasswordDiff", "Passwords are different.");
    }
}
