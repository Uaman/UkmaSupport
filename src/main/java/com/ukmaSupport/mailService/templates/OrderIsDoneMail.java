package com.ukmaSupport.mailService.templates;

import com.ukmaSupport.mailService.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderIsDoneMail {
    private static final String FROM_ADDR = "uukkmmaa.ssuuppoorrtt@gmail.com";  //mail server

    @Autowired
    private MailService mailService;

    public void send(String toAddr/*, String link*/){
        String subject = "Your order is done";
        String body = "Your order is done. Details: " + /*link +*/ "\n\nUKMA Support\nhttp://support-naukma.azurewebsites.net/";

        mailService.sendEmail(toAddr, FROM_ADDR, subject, body);
    }
}
