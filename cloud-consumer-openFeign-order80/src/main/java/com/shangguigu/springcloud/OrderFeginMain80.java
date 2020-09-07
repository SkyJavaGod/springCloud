package com.shangguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 天火
 * @date   2020-06-06
 */
@SpringBootApplication
@EnableFeignClients
public class OrderFeginMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderFeginMain80.class,args);
    }

}
