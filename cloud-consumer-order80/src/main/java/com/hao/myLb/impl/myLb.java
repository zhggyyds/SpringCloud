package com.hao.myLb.impl;

import com.hao.myLb.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhouhao
 * @PackageName:com.hao.myLb.impl
 * @Description:
 * @date 2022/4/18 18:13
 **/

@Component
public class myLb implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        Integer current;
        Integer next;
        do {
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0:current+1;

        }while (!this.atomicInteger.compareAndSet(current,next));
        return next;
    }

    @Override
    public ServiceInstance getServiceInstance(List<ServiceInstance> serviceInstances) {

       int index = getAndIncrement()%serviceInstances.size();
       return serviceInstances.get(index);
    }
}
