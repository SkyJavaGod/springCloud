package com.shangGuiGu.springcloud.service.impl;

import com.shangGuiGu.springcloud.dao.PaymentDao;
import com.shangGuiGu.springcloud.service.PaymentService;
import com.shangguigu.springcloud.commons.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 天火
 * @date   2020-05-05
 */
@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Resource
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
