package com.lc.tacos.messaging;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author DELL
 * @date 2022/10/26 23:14
 */
@Data
public class Taco {
    private String name;
    private Date createdAt;
    private List<Ingredient> ingredients;
}
