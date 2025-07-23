package com.jsp.roam_smart.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.MEMBER; 
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum Role {
        MEMBER, ADMIN
    }
}