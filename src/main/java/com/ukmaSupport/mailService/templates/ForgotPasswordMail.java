package com.ukmaSupport.mailService.templates;

import com.ukmaSupport.mailService.MailService;
import com.ukmaSupport.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ForgotPasswordMail {

    @Autowired
    private MailService mailService;

    public void send(String toAddr, int userId){
        String subject = "Password change ready";
        String link = Constants.SERVER + Constants.CHANGE_PASSWORD + userId;
        String body = "You can change your password: " + link + "\n\nUKMA Support\n" + Constants.SERVER;
        mailService.sendEmail(toAddr, subject, body);
    }
}
