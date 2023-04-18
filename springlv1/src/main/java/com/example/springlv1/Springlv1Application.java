package com.example.springlv1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Springlv1Application {

    public static void main(String[] args) {
        SpringApplication.run(Springlv1Application.class, args);
    }

}
