package com.lc.tacos.messaging;

import com.lc.tacos.domain.Ingredient;
import com.lc.tacos.domain.Order;
import com.lc.tacos.domain.Taco;
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
 * @date 2022/11/28 21:24
 */
@Controller
@RequestMapping("/rabbitmqSend")
@CrossOrigin(origins = "*")
public class OrderSenderController {
    private RabbitOrderMessagingService rabbitOrderMessagingService;

    @Autowired
    public OrderSenderController(RabbitOrderMessagingService rabbitOrderMessagingService) {
        this.rabbitOrderMessagingService = rabbitOrderMessagingService;
    }

    @GetMapping("/order")
    @ResponseBody
    public String convertAndSendOrder() {
        Order order = buildOrder();
        rabbitOrderMessagingService.sendOrder(order);
        System.out.println("转换并发送订单");
        return "发送消息成功！";
    }

    public Order buildOrder() {
        Date date = new Date();
        Order order = new Order();
        order.setPlacedAt(date);
        order.setDeliveryName("通过RabbitMQ发送的消息");
        order.setDeliveryStreet("龙归街");
        order.setDeliveryCity("广州市");
        order.setDeliveryState("广东省");
        order.setDeliveryZip("51000");
        Taco taco = new Taco();
        taco.setName("美味的煎饼");
        taco.setCreatedAt(date);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("3", "酱汁", Ingredient.Type.CHEESE));
        taco.setIngredients(ingredients);
        List<Taco> tacos = new ArrayList<>();
        tacos.add(taco);
        order.setTacos(tacos);
        return order;
    }
}
