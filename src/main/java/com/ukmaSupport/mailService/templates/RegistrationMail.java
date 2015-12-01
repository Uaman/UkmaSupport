package com.ukmaSupport.mailService.templates;


import com.ukmaSupport.mailService.MailService;
import com.ukmaSupport.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMail {

    @Autowired
    private MailService mailService;

    public void send(String toAddr, int userId) {
        String subject = "Welcome to UKMA Support";
        String link = Constants.SERVER + Constants.VERIFICATION + userId;
        String body = "Thank you for registering." + "\n\n" + "Please verify your email by clicking the link below " + link +
                "\n\nUKMA Support\n" + Constants.SERVER;

        mailService.sendEmail(toAddr, subject, body);
    }
}
