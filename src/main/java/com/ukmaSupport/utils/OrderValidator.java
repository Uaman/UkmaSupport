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
//        System.out.println("Title:" + order.getTitle() + "; Workpl:" + order.getWorkplace_access_num() +
//                "; Descr:" + order.getContent() );
        if((order.getTitle() == "") && (order.getContent() == "")
                && (order.getWorkplace_access_num() == null)){
            errors.rejectValue("auditorium", "valid.order.fillAllTheForms");
        }else {
            if (order.getTitle() == "") {
                errors.rejectValue("title", "valid.order.title");
            }else if(order.getTitle().length() < 8) errors.rejectValue("title","valid.order.shortTitle");

            if (order.getWorkplace_access_num() == null) {
                errors.rejectValue("workplace_access_num", "valid.order.workspace");
            }

            if (order.getContent() == "") {
                errors.rejectValue("content", "valid.order.content");
            }else if(order.getTitle().length() < 10) errors.rejectValue("content","valid.order.shortContent");
        }

    }
}
