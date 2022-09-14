package com.hao.service.impl;

import com.hao.dao.PaymentDao;
import entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hao.service.PaymentService;

/**
 * @author zhouhao
 * @PackageName:com.hao.service.impl
 * @Description:
 * @date 2022/4/15 16:52
 **/

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
