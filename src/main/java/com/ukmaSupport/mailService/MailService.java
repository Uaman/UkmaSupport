package com.ukmaSupport.mailService;

import com.ukmaSupport.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class MailService {

    @Autowired
    private MailSender mailSender;  // MailSender interface defines a strateg for sending simple mails

    public void sendEmail(String toAddress, String subject, String msgBody) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(Constants.MAIL_SERVER);
        message.setTo(toAddress);
        message.setSubject(subject);
        message.setText(msgBody);

        mailSender.send(message);
    }

}
