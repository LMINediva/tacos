package com.lc.tacos.kitchen.messaging.kafka.listener;

import com.lc.tacos.domain.Order;
import com.lc.tacos.kitchen.KitchenUI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

/**
 * @author DELL
 * @date 2022/11/29 14:40=
 */
@Profile("kafka-listener")
@Component
@Slf4j
public class OrderListener {
    private KitchenUI ui;

    @Autowired
    public OrderListener(KitchenUI ui) {
        this.ui = ui;
    }

    @KafkaListener(topics = "tacocloud.orders.topic")
    public void handle(Order order, Message<Order> message) {
        MessageHeaders headers = message.getHeaders();
        log.info("从带有时间戳{}的分区{}接收",
                headers.get(KafkaHeaders.RECEIVED_TIMESTAMP),
                headers.get(KafkaHeaders.RECEIVED_PARTITION));
        ui.displayOrder(order);
    }
}
