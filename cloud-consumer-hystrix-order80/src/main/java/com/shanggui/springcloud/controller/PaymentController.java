package com.shanggui.springcloud.controller;

import lombok.extern.slf4j.Slf4j;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.shanggui.springcloud.service.HystrixPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@DefaultProperties(defaultFallback = "paymentInfo_TimeoutDefaultHandler")
public class PaymentController {


    @Autowired
    private HystrixPaymentService hystrixPaymentService;

    @GetMapping("/consumer/hystrix/ok/{id}")
    public String getPaymentOk(@PathVariable Integer id){
        String result = hystrixPaymentService.getPaymentOk(id);
        log.info("****** *****" + result);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="4000")
//    })
    @HystrixCommand
    public String getPaymentTimeOut(@PathVariable Integer id){
        int i = 10/0;
        String result = hystrixPaymentService.getPaymentTimeOut(id);
        log.info("****** *****" + result);
        return result;
    }

    public String paymentInfo_TimeoutHandler(Integer id){
        return "当前支付系统调用超时 id： ，请10后重新请求" + id;

    }

    public String paymentInfo_TimeoutDefaultHandler(){
        return "当前支付系统全局调用超时 id： ，请10后重新请求";

    }
}
