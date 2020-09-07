package com.springCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GateWay9527Main {

    public static void main(String[] args) {
        SpringApplication.run(GateWay9527Main.class,args);
    }
}
