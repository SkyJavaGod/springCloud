package com.shanggui.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "PAYMENT-PROVIDER-HYSTRIX-8001",fallback = FeignFallbackService.class)
public interface HystrixPaymentService {

    @GetMapping("/payment/hystrix/ok/{id}")
    public String getPaymentOk(@PathVariable(value = "id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String getPaymentTimeOut(@PathVariable(value = "id")  Integer id);

}
