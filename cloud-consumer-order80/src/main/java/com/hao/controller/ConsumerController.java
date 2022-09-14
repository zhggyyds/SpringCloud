package com.hao.controller;

import com.hao.myLb.impl.myLb;
import entities.CommonResult;
import entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhouhao
 * @PackageName:com.hao.controller
 * @Description:
 * @date 2022/4/16 18:19
 **/

@RestController
@Slf4j
public class ConsumerController {

    // private static final Object PAYMENT_URL = "http://localhost:8001";
    private static final Object PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private  RestOperations restTemplate;

    @Resource

    private DiscoveryClient discoveryClient;

    @Resource
    private myLb lb;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment)
    {
        return restTemplate.postForObject(PAYMENT_URL +"/payment/create",payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/create/postforentity")
    public CommonResult create1(Payment payment){
        ResponseEntity<CommonResult> res = restTemplate.postForEntity(PAYMENT_URL +"/payment/create",payment, CommonResult.class);

        return res.getBody();
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id)
    {
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/getforentity/{id}")
    public CommonResult<Payment> getPayment1(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> res = restTemplate.getForEntity(PAYMENT_URL +"/payment/get/"+id,CommonResult.class);

        return res.getBody();
    }

    @GetMapping("/consumer/mylb")
    public String lbTest(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances == null || instances.size() == 0){
            log.error("服务不存在");
            return null;
        }

        ServiceInstance instance = lb.getServiceInstance(instances);

        return restTemplate.getForObject(instance.getUri()+"/payment/lb",String.class);
    }

    // ====================> zipkin+sleuth
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin()
    {
        String result = restTemplate.getForObject("http://localhost:8001"+"/payment/zipkin/", String.class);
        return result;
    }

}
