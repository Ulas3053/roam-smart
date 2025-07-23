package com.jsp.roam_smart.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, Object>> handle400(BadRequestException e){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("code", 400);
        map.put("message", e.getMessage());
        return ResponseEntity.status(400).body(map);
        
    }
}
