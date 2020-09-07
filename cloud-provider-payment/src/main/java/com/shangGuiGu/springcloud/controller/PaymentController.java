package com.shangGuiGu.springcloud.controller;

import com.shangGuiGu.springcloud.service.PaymentService;
import com.shangguigu.springcloud.commons.entities.CommonResult;
import com.shangguigu.springcloud.commons.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Resource
    private DiscoveryClient discoveryClient;


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
            return new CommonResult<>(200,"数据失败 使用的服务为" + serverPort,null);
        }else{
            return new CommonResult<>(200,"数据成功 使用的服务为" + serverPort,payment);
        }
    }

    /**
     * 服务发现，查看有多少个服务在提供
     * @return
     */
    @GetMapping(value = "/discovery")
    public Object discovery(){

        List<String> services = discoveryClient.getServices();
        for(String elements: services){
            log.info("/****** elements:" + elements);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance serviceInstance :instances){
            log.info(serviceInstance.getInstanceId() + "\t" + serviceInstance.getHost() + "\t" + serviceInstance.getPort()
                    + "\t" + serviceInstance.getUri());
        }
        return this.discoveryClient;
    }


    @RequestMapping("testTimeout/{id}")
    public String timeout(@PathVariable("id") Long id){

        try {
            TimeUnit.SECONDS.sleep(6L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

}
