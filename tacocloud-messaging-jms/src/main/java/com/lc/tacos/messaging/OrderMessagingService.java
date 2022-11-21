package com.lc.tacos.messaging;

/**
 * @author DELL
 * @date 2022/10/25 22:33
 */
public interface OrderMessagingService {
    /**
     * 发送订单
     *
     * @param order 订单对象
     */
    void sendOrder(Order order);
}
