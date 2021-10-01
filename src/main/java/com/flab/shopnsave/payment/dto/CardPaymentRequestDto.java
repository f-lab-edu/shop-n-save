package com.flab.shopnsave.payment.dto;

import com.flab.shopnsave.payment.domain.CardPayment;
import com.flab.shopnsave.payment.domain.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CardPaymentRequestDto extends PaymentRequestDto {

    @NotBlank(message = "카드번호는 빈 값일 수 없습니다")
    private String cardNumber;

    @NotBlank(message = "카드 비밀번호는 빈 값일 수 없습니다")
    private String cardPassword;

    @Override
    public Payment toEntity() {
        CardPayment cardPayment = CardPayment.builder()
                .cardNumber(cardNumber)
                .cardPassword(cardPassword)
                .orderId(orderId)
                .amount(amount)
                .build();
        return cardPayment;
    }
}
