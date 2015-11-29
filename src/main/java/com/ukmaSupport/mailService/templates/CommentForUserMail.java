package com.ukmaSupport.mailService.templates;

import com.ukmaSupport.mailService.MailService;
import com.ukmaSupport.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentForUserMail {

    @Autowired
    private MailService mailService;

    public void send(String toAddr, int orderId) {
        String subject = "Your order has been commented";
        String link = Constants.SERVER + Constants.COMMENTS + orderId;
        String body = "Your order has been commented. Details: " + link + "\n\nUKMA Support\n" + Constants.SERVER;

        mailService.sendEmail(toAddr, subject, body);
    }
}
