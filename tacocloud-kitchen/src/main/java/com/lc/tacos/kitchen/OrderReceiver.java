package com.lc.tacos.kitchen;

import com.lc.tacos.messaging.Order;

/**
 * @author DELL
 * @date 2022/10/26 22:59
 */
public interface OrderReceiver {
    /**
     * 接收订单数据
     *
     * @return 订单数据
     */
    Order receiveOrder();
}
