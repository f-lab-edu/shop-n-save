package com.flab.shopnsave.payment.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CardPayment extends Payment {

    private String cardNumber;
    private String cardPassword;

    @Builder
    public CardPayment(@Positive long orderId, @Positive int amount, @NotBlank String cardNumber, @NotBlank String cardPassword) {
        this.orderId = orderId;
        this.amount = amount;
        this.cardNumber = cardNumber;
        this.cardPassword = cardPassword;
    }
}
