package com.ukmaSupport.mailService.templates;

import com.ukmaSupport.mailService.MailService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Alex Selivanov on 03.11.2015.
 * General class for sending email from our mail server.
 */
public class Mail {
    private static final String FROM_ADDR = "uukkmmaa.ssuuppoorrtt@mail.ru";  //mail server


    @Autowired
    private MailService mailService;

    private String subject;
    private String body;

    public Mail(String subject, String body){
        this.subject = subject;
        this.body = body;
    }



    public void send(String toAddr) {
        mailService.sendEmail(toAddr, FROM_ADDR, subject, body);
    }
}
