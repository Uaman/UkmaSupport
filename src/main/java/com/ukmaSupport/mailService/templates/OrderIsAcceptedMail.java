package com.ukmaSupport.mailService.templates;


import com.ukmaSupport.mailService.MailService;
import com.ukmaSupport.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderIsAcceptedMail {

    @Autowired
    private MailService mailService;

    public void send(String toAddr/*, String link*/) {
        String subject = "Your order was accepted";
        String body = "Your order was accepted. Details: " + /*link +*/ "\n\nUKMA Support\n" + Constants.LOCAL_SERVER;

        mailService.sendEmail(toAddr, subject, body);
    }
}
