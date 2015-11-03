package com.ukmaSupport.mailService.templates;

/**
 * Created by Alex Selivanov on 03.11.2015.
 *
 * Send email to just registered User for confirmation of registration.
 */
public class RegistrationMail extends Mail {
    private static final String SUBJECT = "Welcome to UKMA Support";
    private static final String BODY = "Hello! Welcome to UKMA Support (+ registration confirmation)";

    public RegistrationMail() {
        super(SUBJECT, BODY);
    }
}
