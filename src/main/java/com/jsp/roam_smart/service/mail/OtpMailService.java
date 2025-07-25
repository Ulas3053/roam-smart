package com.jsp.roam_smart.service.mail;

import jakarta.mail.MessagingException;

public interface OtpMailService {

void sendOtpEmail(String to, int otp,String userName) throws MessagingException;
} 