package com.lc.tacos.kitchen.messaging.jms;

import com.lc.tacos.messaging.Order;
import com.lc.tacos.kitchen.OrderReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * @author DELL
 * @date 2022/10/26 22:56
 */
@Profile("jms-template")
@Component("templateOrderReceiver")
public class JmsOrderReceiver implements OrderReceiver {
    private JmsTemplate jms;

    @Autowired
    public JmsOrderReceiver(JmsTemplate jms) {
        this.jms = jms;
    }

    @Override
    public Order receiveOrder() {
        return (Order) jms.receiveAndConvert("tacocloud.order.queue");
    }
}
