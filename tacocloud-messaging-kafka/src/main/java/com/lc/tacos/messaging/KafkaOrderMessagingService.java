package com.lc.tacos.messaging;

import com.lc.tacos.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author DELL
 * @date 2022/11/29 13:22
 */
@Service
public class KafkaOrderMessagingService
        implements OrderMessagingService {
    private KafkaTemplate<String, Order> kafkaTemplate;

    @Autowired
    public KafkaOrderMessagingService(
            KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendOrder(Order order) {
        //使用该方法可以不用指定主题的名称
        //kafkaTemplate.sendDefault(order);
        kafkaTemplate.send("tacocloud.orders.topic", order);
    }
}
