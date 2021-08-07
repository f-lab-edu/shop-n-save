package com.flab.shopnsave.payment.dto;

import com.flab.shopnsave.payment.domain.Payment;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Getter
public abstract class PaymentRequestDto {

    @Min(value = 1, message = "결제를 진행할 주문 id가 존재해야 합니다")
    protected long orderId;

    @Positive(message = "결제 금액은 0원 이상이어야 합니다")
    protected int amount;

    public abstract Payment toEntity();
}