package com.shanggui.springcloud.service;

import cn.hutool.core.util.IdUtil;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 * 测试使用，不在编写接口，直接使用
 * @author 天火
 * @date   2020-06-10
 */
@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id){
        return "线程池：" + Thread.currentThread().getName() + "当前模拟正常请求ok paymentInfo_OK id" + id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="3000")
    })
    public String paymentInfo_Timeout(Integer id){
        try{
            //int i = 10/0;
            TimeUnit.SECONDS.sleep(2L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "当前模拟超时请求ok  paymentInfo_Timeout id： " + id;
    }

    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程池：" + Thread.currentThread().getName() + "当前模拟超时请求ok   id： " + id;

    }


    /*****************  服务熔断 ***********/


    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties={
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "20"),
    })
    public String paymentCircuitBreaker(@PathVariable Integer id){
        if(id < 0 ){
            throw new RuntimeException("id不能为负数");
        }
        String randomUUID = IdUtil.randomUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功 流水号为" + randomUUID;
    }


    public String paymentCircuitBreaker_fallback(@PathVariable Integer id){
        return "id不能为负数 + jkdsd"+ id ;
    }







}
