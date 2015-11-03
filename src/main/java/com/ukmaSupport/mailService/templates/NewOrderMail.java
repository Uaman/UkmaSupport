package com.ukmaSupport.mailService.templates;

/**
 * Created by Alex Selivanov on 03.11.2015.
 *
 * Sending emails to Lab Assistants about new order.
 */
public class NewOrderMail extends Mail {
    private static final String SUBJECT = "You have new order";
    private static final String BODY = "You have new order (+ some link on this order)";

    public NewOrderMail() {

        super(SUBJECT, BODY);
    }
}
