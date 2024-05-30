package com.sparta.todoapp_project3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig { // passwordConfig 로 변환되어 저장됨

    @Bean
    public PasswordEncoder passwordEncoder() {  // passwordEncoder 는 인터페이스이니까 구현체를 넣어줘야함
        return new BCryptPasswordEncoder();
    }
}