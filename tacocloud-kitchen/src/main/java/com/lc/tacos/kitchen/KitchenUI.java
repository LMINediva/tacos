package com.lc.tacos.kitchen;

import com.lc.tacos.messaging.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author DELL
 * @date 2022/10/27 17:32
 */
@Component
@Slf4j
public class KitchenUI {
    public void displayOrder(Order order) {
        // TODO: 加强这一点，不仅仅是记录接收到的taco。
        // 在某种UI中显示它们
        log.info("接收到的订单：" + order);
    }
}
