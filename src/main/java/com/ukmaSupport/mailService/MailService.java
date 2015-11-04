package com.ukmaSupport.mailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service("mailService")  // for using in such a way - MailService mai;Service = (MailService) context.getBean("mailService");
public class MailService {

    @Autowired
    private MailSender mailSender;  // MailSender interface defines a strateg for sending simple mails

    public void sendEmail(String toAddress, String fromAddress, String subject, String msgBody) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(fromAddress);
        message.setTo(toAddress);
        message.setSubject(subject);
        message.setText(msgBody);

        mailSender.send(message);
    }
}
