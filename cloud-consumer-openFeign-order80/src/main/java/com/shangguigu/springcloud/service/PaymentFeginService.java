package com.shangguigu.springcloud.service;

import com.shangguigu.springcloud.commons.entities.CommonResult;
import com.shangguigu.springcloud.commons.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeginService {

    @RequestMapping("payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @PostMapping("payment/create")
    public CommonResult<Payment> getPaymentById(@RequestBody Payment payment);

    @RequestMapping("payment/testTimeout/{id}")
    public String timeout(@PathVariable("id") Long id);

}
