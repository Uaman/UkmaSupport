package com.ukmaSupport.mailService.templates;

import com.ukmaSupport.mailService.MailService;
import com.ukmaSupport.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewOrderMail {
    @Autowired
    private MailService mailService;

    public void send(String toAddr/*, String link*/) {
        String subject = "You have new order";
        String body = "You have new order. Details: " + /*link +*/ "\n\nUKMA Support\n" + Constants.LOCAL_SERVER;
        mailService.sendEmail(toAddr, subject, body);
    }
}
