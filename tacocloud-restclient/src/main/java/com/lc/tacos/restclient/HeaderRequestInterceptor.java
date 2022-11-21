package com.lc.tacos.restclient;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * 拦截器
 *
 * @author DELL
 * @date 2022/9/26 21:36
 */
public class HeaderRequestInterceptor implements ClientHttpRequestInterceptor {
    private final String headerName;
    private final String headerValue;

    public HeaderRequestInterceptor(String headerName, String headerValue) {
        this.headerName = headerName;
        this.headerValue = headerValue;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().set(headerName, headerValue);
        return execution.execute(request, body);
    }
}
