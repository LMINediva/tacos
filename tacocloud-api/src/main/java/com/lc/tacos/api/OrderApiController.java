package com.lc.tacos.api;

import com.lc.tacos.data.OrderRepository;
import com.lc.tacos.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public OrderApiController(OrderRepository repo) {
        this.repo = repo;
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
}
