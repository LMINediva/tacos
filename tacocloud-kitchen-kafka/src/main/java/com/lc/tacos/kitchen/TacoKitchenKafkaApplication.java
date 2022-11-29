package com.lc.tacos.kitchen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author DELL
 * @date 2022/11/9 22:25
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class TacoKitchenKafkaApplication {
    public static void main(String[] args) {
        SpringApplication.run(TacoKitchenKafkaApplication.class, args);
    }
}
