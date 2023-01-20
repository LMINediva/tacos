package com.lc.tacos.email;

import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author DELL
 * @date 2023/1/18 23:55
 */
@Component
public class OrderSubmitMessageHandler
        implements GenericHandler<Order> {
    private RestTemplate rest;
    private ApiProperties apiProps;

    public OrderSubmitMessageHandler(
            ApiProperties apiProps,
            RestTemplate rest) {
        this.apiProps = apiProps;
        this.rest = rest;
    }

    @Override
    public Object handle(Order order, MessageHeaders headers) {
        rest.postForObject(apiProps.getUrl(), order, String.class);
        return null;
    }
}
