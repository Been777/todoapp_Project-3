package com.sparta.todoapp_project3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class TodoappProject3Application {

    public static void main(String[] args) {
        SpringApplication.run(TodoappProject3Application.class, args);
    }

}
