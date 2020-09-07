package com.shangGuiGu.springcloud.controller;

import com.shangguigu.springcloud.commons.entities.CommonResult;
import com.shangguigu.springcloud.commons.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.shangGuiGu.springcloud.service.PaymentService;

import javax.annotation.Resource;

/**
 * @author 天火
 * @date   2020-05-05
 */
@RestController
@Slf4j
@RequestMapping(value = "payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;



    @RequestMapping("get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){

        Payment payment = paymentService.getPaymentById(id);

        if(null == payment){
            return new CommonResult<>(200,"数据不存在 使用的服务为" + serverPort,null);
        }else{
            return new CommonResult<>(200,"数据不存在 使用的服务为" + serverPort,payment);
        }
    }


    @PostMapping("create")
    public CommonResult<Payment> getPaymentById(@RequestBody Payment payment){

        int i = paymentService.create(payment);

        if(0 == i){
            return new CommonResult<>(200,"创建成功",null);
        }else{
            return new CommonResult<>(200,"",payment);
        }
    }

}
