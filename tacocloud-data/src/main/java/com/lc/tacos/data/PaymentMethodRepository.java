package com.lc.tacos.data;

import com.lc.tacos.domain.PaymentMethod;
import org.springframework.data.repository.CrudRepository;

/**
 * @author DELL
 * @date 2023/1/19 10:08
 */
public interface PaymentMethodRepository extends
        CrudRepository<PaymentMethod, Long> {
    PaymentMethod findByUserId(Long userId);
}
