package com.shanggui.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.shanggui.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @GetMapping("/payment/hystrix/ok/{id}")
    public String getPaymentOk(@PathVariable Integer id){
        String result = paymentService.paymentInfo_OK(id);
        log.info("****** *****" + result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String getPaymentTimeOut(@PathVariable Integer id){
        String result = paymentService.paymentInfo_Timeout(id);
        log.info("****** *****" + result);
        return result;
    }

    @GetMapping("/payment/hystrix/CircuitBreaker/{id}")
    public String getPaymentCircuitBreaker(@PathVariable Integer id){
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("****** *****" + result);
        return result;
    }



}
