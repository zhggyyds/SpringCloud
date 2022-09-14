package com.hao.myLb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {
    public ServiceInstance getServiceInstance(List<ServiceInstance> serviceInstances);
}
