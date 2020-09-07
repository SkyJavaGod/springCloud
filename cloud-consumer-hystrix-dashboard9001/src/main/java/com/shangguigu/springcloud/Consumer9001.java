package com.shangguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class Consumer9001 {

    public static void main(String[] args) {
        SpringApplication.run(Consumer9001.class);
    }
}
