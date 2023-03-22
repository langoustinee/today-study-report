package com.kakao.lango.reactiveweb.interceptor;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class IdentityHeaderInterceptor implements ClientHttpRequestInterceptor {
    private static final String COMPONENT_HEADER_NAME = "X-COMPONENT-ID";
    private static final String COMPONENT_HEADER_VALUE = "LANGO-API";

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        //X-COMPONENT-ID 헤더를 설정하지 않았으면 LANGO-API를 설정
        request.getHeaders().addIfAbsent(COMPONENT_HEADER_NAME, COMPONENT_HEADER_VALUE);
        return execution.execute(request, body);
    }
}
