package com.ukmaSupport.mailService.templates;


import com.ukmaSupport.mailService.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMail {
    private static final String FROM_ADDR = "uukkmmaa.ssuuppoorrtt@gmail.com";  //mail server

    @Autowired
    private MailService mailService;

    public void send(String toAddr, String link){
        String subject = "Welcome to UKMA Support";
        String body = "Welcome to UKMA Support." + "\n\n" + "Please verify your email " + link +
                "\n\nUKMA Support\nhttp://support-naukma.azurewebsites.net/";

        mailService.sendEmail(toAddr, FROM_ADDR, subject, body);
    }
}
