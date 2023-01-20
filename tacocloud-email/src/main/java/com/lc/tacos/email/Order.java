package com.lc.tacos.email;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DELL
 * @date 2023/1/18 21:46
 */
@Data
public class Order {
    private final String email;
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }
}
