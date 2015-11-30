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
        String subject = "New feedback on an assigned order";
        String link = Constants.SERVER + Constants.COMMENTS + orderId;
        String body = "Someone has commented on one of the orders assigned to you. Details: " + link + "\n\nUKMA Support\n" + Constants.SERVER;

        mailService.sendEmail(toAddr, subject, body);
    }
}
