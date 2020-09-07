package com.shangguigu.springcloud.lib.impl;

import com.shangguigu.springcloud.lib.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MySelfLoadBalancer implements LoadBalancer {

    private final AtomicInteger atomicInteger = new AtomicInteger(0);


    private int incrementAndGetModulo() {
        int current;
        int next = 0;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0: current +1;
        } while(!this.atomicInteger.compareAndSet(current, next));
        System.out.println("********** next 值为 " + next);
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstanceList) {

        int index = incrementAndGetModulo()%serviceInstanceList.size();

        return serviceInstanceList.get(index);
    }


}
