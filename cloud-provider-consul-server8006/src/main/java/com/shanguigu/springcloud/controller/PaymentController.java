package com.shanguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author 天火
 * @date   2020-05-05
 */
@RestController
@Slf4j
public class PaymentController {


    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/payment/consul")
    private String getPayment(){
        return "springcloud with consul: "+serverPort+"\t"+ UUID.randomUUID().toString();
    }

}
