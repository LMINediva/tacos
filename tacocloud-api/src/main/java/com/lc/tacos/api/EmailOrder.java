package com.lc.tacos.api;

import lombok.Data;

import java.util.List;

/**
 * @author DELL
 * @date 2023/1/19 0:20
 */
@Data
public class EmailOrder {
    private String email;
    private List<EmailTaco> tacos;

    @Data
    public static class EmailTaco {
        private String name;
        private List<String> ingredients;
    }
}
