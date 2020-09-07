package com.shangguigu.springcloud.lib;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 自定义服务调用算法
 * @author 天火
 * @date   2020-05-30
 */
public interface LoadBalancer {

    ServiceInstance instance(List<ServiceInstance> serviceInstanceList);


}
