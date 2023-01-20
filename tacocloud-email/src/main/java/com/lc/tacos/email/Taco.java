package com.lc.tacos.email;

import lombok.Data;

import java.util.List;

/**
 * @author DELL
 * @date 2023/1/18 21:45
 */
@Data
public class Taco {
    private final String name;
    private List<String> ingredients;
}
