package com.ukmaSupport.mailService.templates;

import com.ukmaSupport.mailService.MailService;
import com.ukmaSupport.models.Order;
import com.ukmaSupport.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewOrderMail {
    @Autowired
    private MailService mailService;

    public void send(String toAddr, int orderId) {
        String subject = "You have a new order";
        String link = Constants.SERVER + Constants.COMMENTS + orderId;
        String body = "You have been assigned a new order." + link + "\n\nUKMA Support\n" + Constants.SERVER;
        mailService.sendEmail(toAddr, subject, body);
    }
}
