package com.lc.tacos.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author DELL
 * @date 2023/1/19 0:13
 */
@SpringBootApplication
public class TacoEmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoEmailApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
