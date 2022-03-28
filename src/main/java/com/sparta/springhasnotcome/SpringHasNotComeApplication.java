package com.sparta.springhasnotcome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableJpaAuditing
@SpringBootApplication
public class SpringHasNotComeApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringHasNotComeApplication.class, args);

    }
}
