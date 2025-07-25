package com.jsp.roam_smart.service.mail.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.jsp.roam_smart.service.mail.OtpMailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Service
public class OtpMailServiceimpl implements OtpMailService {
    
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendOtpEmail(String to,int otp,String userName) throws MessagingException {

        Context context=new Context();
        context.setVariable("otp", otp);
        context.setVariable("userName", userName);

        String htmlContent=templateEngine.process("OtpMailTemplate", context);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject("Your OTP Code"); 
        helper.setText(htmlContent, true);

        javaMailSender.send(message);
    }

    
}