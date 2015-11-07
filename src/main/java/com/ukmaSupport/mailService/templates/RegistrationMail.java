package com.ukmaSupport.mailService.templates;


import com.ukmaSupport.mailService.MailService;
import org.springframework.beans.factory.annotation.Autowired;

public class RegistrationMail {
    private static final String FROM_ADDR = "uukkmmaa.ssuuppoorrtt@gmail.com";  //mail server

    @Autowired
    private MailService mailService;

    public void send(String toAddr/*, String password*/){
        String subject = "Welcome to UKMA Support";
        String body = "Welcome to UKMA Support." + "\n\n" + "Your login: " + toAddr + '\n' + "Your password: " + /*password +*/
                "\n\nUKMA Support\nhttp://support-naukma.azurewebsites.net/";

        mailService.sendEmail(toAddr, FROM_ADDR, subject, body);
    }
}
