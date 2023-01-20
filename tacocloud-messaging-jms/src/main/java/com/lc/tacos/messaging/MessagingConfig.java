package com.lc.tacos.messaging;

import com.lc.tacos.domain.Order;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import javax.jms.ConnectionFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DELL
 * @date 2022/10/26 15:29
 */
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

    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory("tcp://localhost:8083");
    }

    @Bean
    JmsTemplate jmsTemplate(ConnectionFactory connectionFactory,
                            MappingJackson2MessageConverter messageConverter) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setPriority(999);
        jmsTemplate.setMessageConverter(messageConverter);
        return jmsTemplate;
    }
}
