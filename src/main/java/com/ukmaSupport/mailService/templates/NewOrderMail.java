package com.ukmaSupport.mailService.templates;

import com.ukmaSupport.mailService.MailService;
import org.springframework.beans.factory.annotation.Autowired;

public class NewOrderMail {
    private static final String FROM_ADDR = "uukkmmaa.ssuuppoorrtt@gmail.com";  //mail server

    @Autowired
    private MailService mailService;

    public void send(String toAddr/*, String link*/){
        String subject = "You have new order";
        String body = "You have new order. Details: " + /*link +*/ "\n\nUKMA Support\nhttp://support-naukma.azurewebsites.net/";

        mailService.sendEmail(toAddr, FROM_ADDR, subject, body);
    }
}
