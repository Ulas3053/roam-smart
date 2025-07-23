package com.jsp.roam_smart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //ive added spring security config
    //this is a basic config to disable csrf and defaultt form login
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // ❌ Disable CSRF for REST API
            .formLogin(form -> form.disable()) // ❌ Disable Spring’s default login page
            .httpBasic(httpBasic -> httpBasic.disable()) // ❌ Disable browser auth popup
            /* .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll() // ✅ Allow register & login
                .anyRequest().authenticated() // 🔒 Everything else must be authenticated
            ) */
           .authorizeHttpRequests(auth -> auth
    .anyRequest().permitAll()  // Allow everything temporarily
)

            .sessionManagement(sess -> sess
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 🟡 Optional, keeps it stateless
            ); 

        return http.build();
    }

}
