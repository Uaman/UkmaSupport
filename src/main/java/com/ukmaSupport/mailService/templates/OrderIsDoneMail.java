package com.ukmaSupport.mailService.templates;

/**
 * Created by Alex Selivanov on 03.11.2015.
 *
 * Sending emails to User that order is DONE.
 */
public class OrderIsDoneMail extends Mail {
    private static final String SUBJECT = "Your order is DONE";
    private static final String BODY = "Your order is DONE ... (+ some link on this order)";

    public OrderIsDoneMail() {
        super(SUBJECT, BODY);
    }
}
