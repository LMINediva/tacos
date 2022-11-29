package com.lc.tacos.messaging;

import com.lc.tacos.domain.Order;

/**
 * @author DELL
 * @date 2022/11/29 13:20
 */
public interface OrderMessagingService {
    void sendOrder(Order order);
}
