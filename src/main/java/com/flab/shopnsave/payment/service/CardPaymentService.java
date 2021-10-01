package com.flab.shopnsave.payment.service;

import com.flab.shopnsave.member.domain.AuthMember;
import com.flab.shopnsave.order.OrderService;
import com.flab.shopnsave.order.domain.Order;
import com.flab.shopnsave.payment.domain.Payment;
import com.flab.shopnsave.payment.dto.PaymentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardPaymentService implements PaymentService {

    private final OrderService orderService;

    InnerAbstractPaymentService innerAbstractPaymentService = new InnerAbstractPaymentService();

    @Override
    public void pay(PaymentRequestDto paymentRequestDto, AuthMember authMember) {
        Payment payment = paymentRequestDto.toEntity();
        Order order = orderService.getById(payment.getOrderId());
        innerAbstractPaymentService.pay(payment, order, authMember);
    }

    @Override
    public void verifyStatus(Order order) {
        innerAbstractPaymentService.verifyStatus(order);
    }

    @Override
    public void verifyAmount(Payment payment, Order order) {
        innerAbstractPaymentService.verifyAmount(payment, order);
    }

    @Override
    public void verifyUser(AuthMember authMember, Order order) {
        innerAbstractPaymentService.verifyUser(authMember, order);
    }

    @Override
    public void payProcess(Payment payment, Order order) {
        // TODO
        /* 주문 상태가 변경된다.(WAITING_PAYMENT -> PREPARING_DELIVERY)
            방안 [1]
            order.paymentComplete(); // order instance 상태 변경
            orderMapper.updateMember();

            방안 [2]
            orderMapper.updateStatus();
        */

        // 결제 상태가 변경된다.(결제 대기 -> 결제 완료)
        // DB 반영
    }

    private class InnerAbstractPaymentService extends AbstractPaymentService {

        @Override
        public void payProcess(Payment payment, Order order) {
            CardPaymentService.this.payProcess(payment, order);
        }
    }
}
