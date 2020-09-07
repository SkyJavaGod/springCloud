package com.shanggui.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 天火
 * @date   2020-05-15
 */
@SpringBootApplication
@EnableEurekaServer
public class MainEureka7002 {
    public static void main(String[] args) {
        SpringApplication.run(MainEureka7002.class,args);
    }
}
