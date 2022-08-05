package com.lc.tacos.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author DELL
 * @date 2022/4/25 23:38
 */
@Profile("!prod")
@Configuration
public class DevelopmentConfig {
    @Bean
    public CommandLineRunner dataLoader() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                System.out.println("命令行启动，添加配料数据。");
            }
        };
    }
}
