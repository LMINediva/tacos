package com.lc.tacos.data;

import com.lc.tacos.domain.Order;
import com.lc.tacos.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author DELL
 * @date 2022/4/19 17:16
 */
public interface OrderRepository extends
        CrudRepository<Order, Long> {

    List<Order> findByUserOrderByPlacedAtDesc(
            User user, Pageable pageable);

}
