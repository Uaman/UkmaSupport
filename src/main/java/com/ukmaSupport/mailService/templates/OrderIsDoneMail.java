package com.ukmaSupport.mailService.templates;

import com.ukmaSupport.mailService.MailService;
import com.ukmaSupport.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderIsDoneMail {

    @Autowired
    private MailService mailService;

    public void send(String toAddr/*, String link*/) {
        String subject = "Your order is done";
        String body = "Your order is done. Details: " + /*link +*/ "\n\nUKMA Support\n" + Constants.SERVER;

        mailService.sendEmail(toAddr, subject, body);
    }
}
