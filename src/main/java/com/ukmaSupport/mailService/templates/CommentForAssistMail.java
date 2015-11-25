package com.ukmaSupport.mailService.templates;

import com.ukmaSupport.mailService.MailService;
import com.ukmaSupport.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentForAssistMail {
    private static final String FROM_ADDR = Constants.MAIL_SERVER;

    @Autowired
    private MailService mailService;

    public void send(String toAddr, int orderId){
        String subject = "Order assigned to you has been commented";
        String link = Constants.SERVER + Constants.COMMENTS + orderId;
        String body = "Order assigned to you has been commented. Details: " + link + "\n\nUKMA Support\n" + Constants.SERVER;

        mailService.sendEmail(toAddr, FROM_ADDR, subject, body);
    }
}
