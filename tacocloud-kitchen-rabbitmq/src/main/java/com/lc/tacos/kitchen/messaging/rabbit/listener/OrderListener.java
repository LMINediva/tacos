package com.lc.tacos.kitchen.messaging.rabbit.listener;

import com.lc.tacos.domain.Order;
import com.lc.tacos.kitchen.KitchenUI;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author DELL
 * @date 2022/11/29 0:08
 */
@Profile("rabbitmq-listener")
@Component
public class OrderListener {

    private KitchenUI ui;

    @Autowired
    public OrderListener(KitchenUI ui) {
        this.ui = ui;
    }

    @RabbitListener(queues = "tacocloud.order.queue")
    public void receiveOrder(Order order) {
        ui.displayOrder(order);
    }
}
