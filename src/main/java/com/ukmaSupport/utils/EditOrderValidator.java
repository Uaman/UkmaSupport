package com.ukmaSupport.utils;

import com.ukmaSupport.models.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EditOrderValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Order.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Order order = (Order)o;

        if(order.getTitle() == null || order.getTitle().length()<8){
            errors.rejectValue("title","valid.order.title");
        }
        if(order.getContent() == null || order.getTitle().length()<10){
            errors.rejectValue("content","valid.order.content");
        }

    }
}
