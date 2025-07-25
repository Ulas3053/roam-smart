package com.jsp.roam_smart.service.mail.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.jsp.roam_smart.service.mail.RegestrationEmail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.thymeleaf.context.Context;
@Service
public class RegestrationEmailImpl implements RegestrationEmail {

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendRegestrationMail(String to,String name,String phone) throws MessagingException{
        Context context = new Context();
        context.setVariable("userName", name); 
        context.setVariable("phone", phone);
        context.setVariable("email", to);

        String htmlContent=templateEngine.process("RegestrationEmailTemplate", context);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject("Thank you for registering with RoamSmart");
        helper.setText(htmlContent, true);

        javaMailSender.send(message);
    }
    
    
}
