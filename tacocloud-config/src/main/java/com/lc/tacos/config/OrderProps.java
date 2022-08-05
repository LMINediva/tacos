package com.lc.tacos.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author DELL
 * @date 2022/4/25 22:01
 */
@Component
@ConfigurationProperties(prefix = "taco.orders")
@Data
@Validated
public class OrderProps {

    @Min(value = 5, message = "必须在5到25之间")
    @Max(value = 25, message = "必须在5到25之间")
    private int pageSize = 20;
}
