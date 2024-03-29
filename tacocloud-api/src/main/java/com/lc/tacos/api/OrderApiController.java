package com.lc.tacos.api;

import com.lc.tacos.data.OrderRepository;
import com.lc.tacos.domain.Order;
import com.lc.tacos.messaging.OrderMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author DELL
 * @date 2022/8/6 15:33
 */
@RestController
@RequestMapping(path = "/orders",
        produces = "application/json")
@CrossOrigin(origins = "*")
public class OrderApiController {
    private OrderRepository repo;
    private OrderMessagingService orderMessages;
    private EmailOrderService emailOrderService;

    @Autowired
    public OrderApiController(OrderRepository repo,
                              OrderMessagingService orderMessages,
                              EmailOrderService emailOrderService) {
        this.repo = repo;
        this.orderMessages = orderMessages;
        this.emailOrderService = emailOrderService;
    }

    @GetMapping(produces = "application/json")
    public Iterable<Order> allOrders() {
        return repo.findAll();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Order postOrder(@RequestBody Order order) {
        return repo.save(order);
    }

    @PutMapping(path = "/{orderId}", consumes = "application/json")
    public Order putOrder(@RequestBody Order order) {
        return repo.save(order);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public Order patchOrder(@PathVariable("orderId") Long orderId,
                            @RequestBody Order patch) {
        Order order = repo.findById(orderId).get();
        if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
        }

        if (patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }

        if (patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }

        if (patch.getDeliveryState() != null) {
            order.setDeliveryState(patch.getDeliveryState());
        }

        if (patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryZip());
        }

        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }

        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }

        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }

        return repo.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            repo.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {
        }
    }

    /**
     * 通过Email发送墨西哥煎饼卷订单
     */
    @PostMapping(path = "fromEmail", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Order postOrderFromEmail(@RequestBody EmailOrder emailOrder) {
        Order order = emailOrderService.convertEmailOrderToDomainOrder(emailOrder);
        orderMessages.sendOrder(order);
        return repo.save(order);
    }
}
