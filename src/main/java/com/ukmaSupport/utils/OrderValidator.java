package com.ukmaSupport.utils;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Dima on 13.11.2015.
 */
public class OrderValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}
