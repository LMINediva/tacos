package com.lc.tacos.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DELL
 * @date 2022/4/25 23:49
 */
@Component
@ConfigurationProperties(prefix = "taco.discount")
@Data
public class DiscountCodeProps {

    private Map<String, Integer> codes = new HashMap<>();

}
