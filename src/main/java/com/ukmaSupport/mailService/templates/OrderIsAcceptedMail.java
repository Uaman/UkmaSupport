package com.ukmaSupport.mailService.templates;

/**
 * Created by Alex Selivanov on 03.11.2015.
 *
 * Sending emails to Users about accepting of their orders.
 */
public class OrderIsAcceptedMail extends Mail {
    private static final String SUBJECT = "Accepted order";
    private static final String BODY = "Lab. Assistant has accepted your order. (+ some link on this order)";

    public OrderIsAcceptedMail() {

        super(SUBJECT, BODY);
    }
}
