package com.shangguigu.springcloud.controller;

import com.shangguigu.springcloud.commons.entities.CommonResult;
import com.shangguigu.springcloud.commons.entities.Payment;
import com.shangguigu.springcloud.service.PaymentFeginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private PaymentFeginService paymentFeginService;


    @RequestMapping("/create")
    public CommonResult<Payment> createPayment(Payment payment){

        return paymentFeginService.getPaymentById(payment);
    }

    @RequestMapping("get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeginService.getPaymentById(id);
    }

    @RequestMapping("timeout/{id}")
    public String timeout(@PathVariable("id") Long id){
        return paymentFeginService.timeout(id);
    }







}


