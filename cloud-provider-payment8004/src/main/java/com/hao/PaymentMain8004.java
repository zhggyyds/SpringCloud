package com.hao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhouhao
 * @PackageName:com.hao
 * @Description:
 * @date 2022/4/17 19:21
 **/
@SpringBootApplication
@EnableDiscoveryClient //向使用consul或者zookeeper作为注册中心时都需要用到
public class PaymentMain8004
{
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8004.class, args);
    }
}
