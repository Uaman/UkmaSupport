package com.ukmaSupport.mailService.templates;

import com.ukmaSupport.mailService.MailService;
import com.ukmaSupport.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentForAssistMail {

    @Autowired
    private MailService mailService;

    public void send(String toAddr, int orderId) {
        String subject = "Order assigned to you has been commented";
        String link = Constants.LOCAL_SERVER + Constants.COMMENTS + orderId;
        String body = "Order assigned to you has been commented. Details: " + link + "\n\nUKMA Support\n" + Constants.LOCAL_SERVER;

        mailService.sendEmail(toAddr, subject, body);
    }
}
