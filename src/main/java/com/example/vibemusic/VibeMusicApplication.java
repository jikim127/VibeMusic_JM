package com.example.vibemusic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VibeMusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(VibeMusicApplication.class, args);
    }

}
