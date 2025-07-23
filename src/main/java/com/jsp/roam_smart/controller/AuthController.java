package com.jsp.roam_smart.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

public class AuthController {
    public ResponseEntity<Map<String, Object>> register(){
        Map<String,Object> map =new LinkedHashMap<>();
        return ResponseEntity.status(201).body(map);
    }
}
