package com.ukmaSupport.utils;

import com.ukmaSupport.models.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class OrderValidator implements Validator {



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
        if(order.getWorkplace_access_num() == null){
            errors.rejectValue("workplace_access_num","valid.order.workspace");
        }
        if(order.getContent() == null || order.getTitle().length()<10){
            errors.rejectValue("content","valid.order.content");
        }

    }
}
