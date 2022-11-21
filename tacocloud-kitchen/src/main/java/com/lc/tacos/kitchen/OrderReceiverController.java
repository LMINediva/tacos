package com.lc.tacos.kitchen;

import com.lc.tacos.messaging.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author DELL
 * @date 2022/11/9 22:16
 */
@Profile({"jms-template"})
@Controller
@RequestMapping("/convertAndReceive")
@RequiredArgsConstructor
public class OrderReceiverController {
    private final OrderReceiver orderReceiver;

    @GetMapping("/receive")
    public String receiveOrder(Model model) {
        System.out.println("接收到数据");
        Order order = orderReceiver.receiveOrder();
        if (order != null) {
            model.addAttribute("order", order);
            return "receiveOrder";
        }
        return "noOrder";
    }
}
