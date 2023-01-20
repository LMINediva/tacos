package com.lc.tacos.api;

import com.lc.tacos.data.IngredientRepository;
import com.lc.tacos.data.PaymentMethodRepository;
import com.lc.tacos.data.UserRepository;
import com.lc.tacos.domain.*;
import org.springframework.stereotype.Service;
import com.lc.tacos.api.EmailOrder.EmailTaco;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author DELL
 * @date 2023/1/19 0:25
 */
@Service
public class EmailOrderService {
    private UserRepository userRepo;
    private IngredientRepository ingredientRepo;
    private PaymentMethodRepository paymentMethodRepo;

    public EmailOrderService(
            UserRepository userRepo,
            IngredientRepository ingredientRepo,
            PaymentMethodRepository paymentMethodRepo) {
        this.userRepo = userRepo;
        this.ingredientRepo = ingredientRepo;
        this.paymentMethodRepo = paymentMethodRepo;
    }

    public Order convertEmailOrderToDomainOrder(EmailOrder emailOrder) {
        // TODO:可能应该处理不愉快的情况下，电子邮件地址不匹配给定的用户或
        // 用户至少没有一种支付方式
        User user = userRepo.findByEmail(emailOrder.getEmail());
        PaymentMethod paymentMethod = paymentMethodRepo.findByUserId(user.getId());

        Order order = new Order();
        order.setUser(user);
        order.setCcNumber(paymentMethod.getCcNumber());
        order.setCcCVV(paymentMethod.getCcCVV());
        order.setCcExpiration(paymentMethod.getCcExpiration());
        order.setDeliveryName(user.getFullname());
        order.setDeliveryStreet(user.getStreet());
        order.setDeliveryCity(user.getCity());
        order.setDeliveryState(user.getState());
        order.setDeliveryZip(user.getZip());
        order.setPlacedAt(new Date());

        // TODO:处理不愉快的情况下，给定的成分不匹配
        List<EmailTaco> emailTacos = emailOrder.getTacos();
        for (EmailTaco emailTaco : emailTacos) {
            Taco taco = new Taco();
            taco.setName(emailTaco.getName());
            List<String> ingredientIds = emailTaco.getIngredients();
            List<Ingredient> ingredients = new ArrayList<>();
            for (String ingredientId : ingredientIds) {
                Optional<Ingredient> optionalIngredient =
                        ingredientRepo.findById(ingredientId);
                if (optionalIngredient.isPresent()) {
                    ingredients.add(optionalIngredient.get());
                }
            }
            taco.setIngredients(ingredients);
            order.addDesign(taco);
        }

        return order;
    }
}
