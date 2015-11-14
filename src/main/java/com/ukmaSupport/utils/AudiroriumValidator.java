package com.ukmaSupport.utils;

import com.ukmaSupport.models.Auditorium;
import com.ukmaSupport.services.interfaces.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AudiroriumValidator implements Validator {
    @Autowired
    private AuditoriumService auditoriumService;
    @Override
    public boolean supports(Class<?> aClass) {
        return Auditorium.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Auditorium form=( Auditorium) obj;
        if (auditoriumService.getByNumber(form.getNumber()) != null)
            errors.rejectValue("number", "valid.number", "Auditorium is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "number", "valid.number","This auditorium exists in database");

    }
}
