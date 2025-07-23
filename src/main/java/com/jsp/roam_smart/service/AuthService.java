package com.jsp.roam_smart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jsp.roam_smart.exception.BadRequestException;
import com.jsp.roam_smart.dto.UserDTO;
import com.jsp.roam_smart.model.User;
import com.jsp.roam_smart.repository.UserRepository;
@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public void register(UserDTO userDTO) {
        
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new BadRequestException("Email already exists");
        }
        User user=User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();
        userRepository.save(user);

    }
    
}
