package com.shangGuiGu.springcloud.service;


import com.shangguigu.springcloud.commons.entities.Payment;

/**
 * @author 天火
 * @date   19:17
 */
public interface PaymentService {

    /**
     * 增加测试订单信息
     * @param payment
     * @return
     */
    public int create(Payment payment);

    /**
     * 查询信息根据订单id查询
     * @param id
     * @return
     */
    public Payment getPaymentById(Long id);

}


