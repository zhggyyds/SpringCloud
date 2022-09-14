package com.hao.controller;

import com.hao.service.PaymentService;
import entities.CommonResult;
import entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB()
    {
        return serverPort;
    }
}
