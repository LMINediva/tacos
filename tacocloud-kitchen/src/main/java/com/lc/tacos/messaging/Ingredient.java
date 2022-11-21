package com.lc.tacos.messaging;

import lombok.Data;

/**
 * @author DELL
 * @date 2022/10/26 23:03
 */
@Data
public class Ingredient {
    private final String name;
    private final Type type;

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
