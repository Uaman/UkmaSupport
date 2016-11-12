package com.ukmaSupport.utils;

import com.ukmaSupport.models.User;
import com.ukmaSupport.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RegistrationValidator implements Validator {
    @Autowired
    private UserService userDao;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String FIO_PATTERN = "[\\p{InCyrillic}|']+";
    private static final String PASSWORD_PATTERN = "((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";

    public boolean supports(Class<?> paramClass) {
        return User.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        User form = (User) obj;
        if (form.getFirstName().equals("") || form.getLastName().equals("") || form.getEmail().equals("") || form.getPassword().equals("") || form.getConfPassword().equals(""))
            errors.rejectValue("dateOfEntry", "valid.order.fillAllTheForms");
        else if (!form.getFirstName().matches(FIO_PATTERN))
            errors.rejectValue("firstName", "valid.firstName", "FirstName is required.");
        else if (!form.getLastName().matches(FIO_PATTERN))
            errors.rejectValue("lastName", "valid.lastName", "LastName is required.");
        else if (!form.getEmail().matches(EMAIL_PATTERN))
            errors.rejectValue("email", "valid.email", "Email is required.");
        else if (userDao.getByEmail(form.getEmail()) != null)
            errors.rejectValue("email", "valid.duplicatedEmail", "Email is required.");
        else if (form.getPassword().length() < 8)
            errors.rejectValue("password", "valid.password.length", "Incorrect password");
        else if (!form.getPassword().matches(PASSWORD_PATTERN))
            errors.rejectValue("password", "valid.password2", "Incorrect password");
        else if (!form.getPassword().equals(form.getConfPassword()))
            errors.rejectValue("confPassword", "valid.confPasswordDiff", "Passwords are different.");

    }
}