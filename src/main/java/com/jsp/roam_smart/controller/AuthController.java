package com.jsp.roam_smart.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.roam_smart.dto.UserDTO;
import com.jsp.roam_smart.service.AuthService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // idu meanns can access through any frontend
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody UserDTO userDTO, HttpSession session)
            throws MessagingException {
        authService.register(userDTO, session);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("message", "check email for otp");
        // map.put("user", userDTO);
        return ResponseEntity.status(201).body(map);
    }

    @PostMapping("/otp")
    public ResponseEntity<Map<String, Object>> verifyOtp(@RequestParam int otp, HttpSession session)
            throws MessagingException {
        authService.verifyOtp(otp, session);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("message", "OTP verified successfully");
        map.put("user", session.getAttribute("userDTO"));
        return ResponseEntity.status(201).body(map);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        String result = authService.login(email, password); // returns success or throws
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("message", result);
        // map.put("token", jwtToken); // Add this later if you implement JWT

        return ResponseEntity.ok(map);
    }

}
