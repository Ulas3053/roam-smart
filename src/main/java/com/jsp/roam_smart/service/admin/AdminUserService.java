package com.jsp.roam_smart.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jsp.roam_smart.model.User;
@Service
public interface AdminUserService {

    public List<User> getUserDetails();   
}
