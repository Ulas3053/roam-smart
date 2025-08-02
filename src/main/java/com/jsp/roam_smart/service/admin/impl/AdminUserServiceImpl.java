package com.jsp.roam_smart.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.roam_smart.model.User;
import com.jsp.roam_smart.repository.UserRepository;
import com.jsp.roam_smart.service.admin.AdminUserService;
@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private UserRepository userRepository; 

    @Override
   public List<User> getUserDetails() {
    return userRepository.findByRole(User.Role.MEMBER);
}
    
}
