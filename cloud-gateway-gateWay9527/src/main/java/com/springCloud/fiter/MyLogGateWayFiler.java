package com.springCloud.fiter;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
@Slf4j
public class MyLogGateWayFiler implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("测试自己定义的过滤器 ********************* 输出");

        String username =  exchange.getRequest().getQueryParams().getFirst("uname");

        if(username == null){
            log.info("用户未登录，用户登录后重试请求");
            exchange.getResponse().setStatusCode(HttpStatus.MULTI_STATUS);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
