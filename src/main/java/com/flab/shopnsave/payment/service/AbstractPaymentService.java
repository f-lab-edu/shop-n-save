package com.flab.shopnsave.payment.service;

import com.flab.shopnsave.member.domain.AuthMember;
import com.flab.shopnsave.member.exception.ForbiddenException;
import com.flab.shopnsave.order.domain.Order;
import com.flab.shopnsave.payment.domain.Payment;
import com.flab.shopnsave.payment.dto.PaymentRequestDto;
import com.flab.shopnsave.payment.exception.NonMatchPaymentAmount;
import com.flab.shopnsave.payment.exception.NonPayableOrderStatus;

/*
    추상 골격 구현 클래스를 제공하여 인터페이스와 추상 클래스의 장점(공통 메서드 구현, 템플릿 메서드 패턴)을 동시에 취합니다.
 */
public abstract class AbstractPaymentService implements PaymentService {

    public void pay(Payment payment, Order order, AuthMember authMember) {
        verifyStatus(order);
        verifyAmount(payment, order);
        verifyUser(authMember, order);
        payProcess(payment, order);
    }

    @Override
    public void verifyStatus(Order order) {
        if (!order.isAvailablePay()) {
            throw new NonPayableOrderStatus(order.getStatus().getCode());
        }
    }

    @Override
    public void verifyAmount(Payment payment, Order order) {
        int requestAmount = payment.getAmount();
        int totalPrice = order.getTotalPrice();
        if(requestAmount != totalPrice) {
            throw new NonMatchPaymentAmount(requestAmount, totalPrice);
        }
    }

    @Override
    public void verifyUser(AuthMember authMember, Order order) {
        if(!authMember.getId().equals(order.getOrdererId())) {
            throw new ForbiddenException();
        }
    }

    @Override
    public abstract void payProcess(Payment payment, Order order);

    @Override
    public void pay(PaymentRequestDto paymentRequestDto, AuthMember authMember) {
    }
}