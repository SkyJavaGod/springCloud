package com.shangguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 替换默认的负载均衡规则
 */
@Configuration
public class Myrule {

    @Bean
    public IRule getIRule(){
        return new RandomRule();

    }
}
