package com.shanggui.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * feign 服务fallback方法
 */
@Component
public class FeignFallbackService implements HystrixPaymentService {
    @Override
    public String getPaymentOk(Integer id) {
        return "feign 调用服务8001 getPaymentOk异常: o(╥﹏╥)o 失败";
    }

    @Override
    public String getPaymentTimeOut(Integer id) {
        return "feign 调用服务8001 getPaymentTimeOut异常: o(╥﹏╥)o 失败";
    }
}
