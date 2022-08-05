package com.lc.tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.lc.tacos")
public class TacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacosApplication.class, args);
    }
}