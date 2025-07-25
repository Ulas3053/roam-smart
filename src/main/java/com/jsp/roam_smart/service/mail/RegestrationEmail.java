package com.jsp.roam_smart.service.mail;

import jakarta.mail.MessagingException;

public interface RegestrationEmail {
    void sendRegestrationMail(String to,String name,String phone) throws MessagingException;

    
}