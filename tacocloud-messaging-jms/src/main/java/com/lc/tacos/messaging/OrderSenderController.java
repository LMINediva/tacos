package com.lc.tacos.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author DELL
 * @date 2022/10/27 17:55
 */
@Controller
@RequestMapping(path = "/convertAndSend")
@CrossOrigin(origins = "*")
public class OrderSenderController {
    private JmsOrderMessagingService jmsOrderMessagingService;

    @Autowired
    public OrderSenderController(JmsOrderMessagingService jmsOrderMessagingService) {
        this.jmsOrderMessagingService = jmsOrderMessagingService;
    }

    @GetMapping("/order")
    @ResponseBody
    public String convertAndSendOrder() {
        Order order = buildOrder();
        jmsOrderMessagingService.sendOrder(order);
        System.out.println("转换并发送订单");
        return "发送消息成功";
    }

    public Order buildOrder() {
        Date date = new Date();
        Order order = new Order();
        order.setPlacedAt(date);
        order.setDeliveryName("通过AMQP发送的消息");
        order.setDeliveryStreet("龙归街");
        order.setDeliveryCity("广州市");
        order.setDeliveryState("广东省");
        order.setDeliveryZip("51000");
        Taco taco = new Taco();
        taco.setName("美味的煎饼");
        taco.setCreatedAt(date);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("酱料", Ingredient.Type.CHEESE));
        taco.setIngredients(ingredients);
        List<Taco> tacos = new ArrayList<>();
        order.setTacos(tacos);
        return order;
    }
}
