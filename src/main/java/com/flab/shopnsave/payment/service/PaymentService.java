package com.flab.shopnsave.payment.service;

import com.flab.shopnsave.member.domain.AuthMember;
import com.flab.shopnsave.order.domain.Order;
import com.flab.shopnsave.payment.domain.Payment;
import com.flab.shopnsave.payment.dto.PaymentRequestDto;

public interface PaymentService {

    public void pay(PaymentRequestDto paymentRequestDto, AuthMember authMember);

    public void verifyStatus(Order order);

    public void verifyAmount(Payment payment, Order order);

    public void verifyUser(AuthMember authMember, Order order);

    public void payProcess(Payment payment, Order order);
}
