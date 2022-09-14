package com.hao.controller;

import com.hao.service.PaymentService;
import com.netflix.appinfo.InstanceInfo;
import entities.CommonResult;
import entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouhao
 * @PackageName:com.hao.controller
 * @Description:
 * @date 2022/4/15 16:55
 **/

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);

        log.info(" 插入操作返回结果 :{}",result);

        if (result > 0){
            return new CommonResult(200,"插入成功:serverPort"+serverPort,result);
        }else{
            return new CommonResult(444,"插入失败");
        }
    }

    @GetMapping("/payment/get/{id}" )
    public CommonResult<Payment> getPaymentById(@PathVariable( "id" ) Long id){
        Payment payment = paymentService .getPaymentById(id);
        if(payment !=null ) {
            return new CommonResult(200, " 查询成功:serverPort"+serverPort, payment);
        } else {
            return new CommonResult(444, " 没有对应记录 , 查询 ID: " + id);
        }
    }



    @GetMapping("/payment/discovery")
    public void discovery(){

        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("*****element: "+element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB()
    {
        return serverPort;
    }


    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout()
    {
        // 业务逻辑处理正确，但是需要耗费3秒钟
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin()
    {
        return "hi ,i'am paymentzipkin server fall back，welcome to atguigu，O(∩_∩)O哈哈~";
    }
}
