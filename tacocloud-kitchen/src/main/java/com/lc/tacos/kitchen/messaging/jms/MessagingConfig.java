package com.lc.tacos.kitchen.messaging.jms;

import com.lc.tacos.messaging.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DELL
 * @date 2022/10/26 15:29
 */
@Profile({"jms-template"})
@Configuration
public class MessagingConfig {

    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        MappingJackson2MessageConverter messageConverter =
                new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("_typeId");

        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("order", Order.class);
        messageConverter.setTypeIdMappings(typeIdMappings);
        return messageConverter;
    }
}
