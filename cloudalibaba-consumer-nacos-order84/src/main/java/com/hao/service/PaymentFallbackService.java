package com.hao.service;

import entities.CommonResult;
import entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @author zhouhao
 * @PackageName:com.hao.service
 * @Description:
 * @date 2022/5/13 10:05
 **/

@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public CommonResult<Payment> paymentSQL(Long id)
    {
        return new CommonResult<>(44444,"服务降级返回,---PaymentFallbackService",new Payment(id,"errorSerial"));
    }
}
