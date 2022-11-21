package com.lc.tacos.messaging;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author DELL
 * @date 2022/10/26 23:01
 */
@Data
public class Order {
    private Date placedAt;
    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    private List<Taco> tacos = new ArrayList<>();
}
