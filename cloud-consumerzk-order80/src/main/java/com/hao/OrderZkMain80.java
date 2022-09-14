package com.hao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhouhao
 * @PackageName:com.hao
 * @Description:
 * @date 2022/4/17 20:42
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class OrderZkMain80
{
    public static void main(String[] args) {
        SpringApplication.run(OrderZkMain80.class, args);
    }
}
