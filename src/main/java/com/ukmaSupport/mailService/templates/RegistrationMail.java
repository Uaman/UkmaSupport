package com.ukmaSupport.mailService.templates;


import com.ukmaSupport.mailService.MailService;
import com.ukmaSupport.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMail {
    private static final String FROM_ADDR = Constants.MAIL_SERVER;  //mail server

    @Autowired
    private MailService mailService;

    public void send(String toAddr, int userId){
        String subject = "Welcome to UKMA Support";
        String link = Constants.LOCAL_SERVER + Constants.VERIFICATION + userId;
        String body = "Welcome to UKMA Support." + "\n\n" + "Please verify your email " + link +
                "\n\nUKMA Support\n" + Constants.SERVER;

        mailService.sendEmail(toAddr, FROM_ADDR, subject, body);
    }
}
