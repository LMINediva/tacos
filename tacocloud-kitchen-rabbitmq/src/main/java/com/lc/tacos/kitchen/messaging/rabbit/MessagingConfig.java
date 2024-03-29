package com.lc.tacos.kitchen.messaging.rabbit;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author DELL
 * @date 2022/11/28 17:29
 */
@Profile({"rabbitmq-template", "rabbitmq-listener"})
@Configuration
public class MessagingConfig {

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
