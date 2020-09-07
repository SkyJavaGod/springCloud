package com.shangguigu.springcloud.controller;

import com.shangguigu.springcloud.commons.entities.CommonResult;
import com.shangguigu.springcloud.commons.entities.Payment;
import com.shangguigu.springcloud.lib.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 天火
 * @date   2020-05-15 
 */
@RestController
@Slf4j
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancer loadBalancer;


    private static final String HOST_URL = "http://CLOUD-PAYMENT-SERVICE";

    @RequestMapping("/create")
    public CommonResult<Payment> createPayment(Payment payment){

        return restTemplate.postForObject(HOST_URL+ "/payment/create",payment,CommonResult.class );
    }

    @RequestMapping("/createRibbon")
    public CommonResult<Payment> createPayment1(Payment payment){
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(HOST_URL + "/payment/create", payment, CommonResult.class);
        log.info(String.valueOf(entity.getStatusCode()));
        return entity.getBody();
    }


    @RequestMapping("/getRibbon/{id}")
    public CommonResult<Payment> getPayment1(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(HOST_URL + "/payment/get/" + id, CommonResult.class);
        log.info(String.valueOf(entity.getStatusCode()));
        return entity.getBody();
    }

    /**
     * 服务发现，查看有多少个服务在提供
     * @return
     */
    @GetMapping(value = "/discovery")
    public String discovery(){

        List<String> services = discoveryClient.getServices();
        for(String elements: services){
            log.info("/****** elements:" + elements);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        if(instances == null ||instances.size()< 0 ){
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instance(instances);
            log.info(serviceInstance.getInstanceId() + "\t" + serviceInstance.getHost() + "\t" + serviceInstance.getPort()
                    + "\t" + serviceInstance.getUri());

        return String.valueOf(serviceInstance.getPort());
    }
}
