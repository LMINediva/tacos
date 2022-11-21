package com.lc.tacos.kitchen.messaging.jms.listener;

import com.lc.tacos.messaging.Order;
import com.lc.tacos.kitchen.KitchenUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author DELL
 * @date 2022/10/27 17:36
 */
@Profile("jms-listener")
@Component
public class OrderListener {
    private KitchenUI ui;

    @Autowired
    public OrderListener(KitchenUI ui) {
        this.ui = ui;
    }

    @JmsListener(destination = "tacocloud.order.queue")
    public void receiveOrder(Order order) {
        ui.displayOrder(order);
    }
}
