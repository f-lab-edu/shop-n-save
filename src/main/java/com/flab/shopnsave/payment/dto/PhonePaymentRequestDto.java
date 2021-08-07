package com.flab.shopnsave.payment.dto;

import com.flab.shopnsave.payment.domain.Payment;
import com.flab.shopnsave.payment.domain.PhonePayment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class PhonePaymentRequestDto extends PaymentRequestDto {

    @NotBlank(message = "핸드폰 번호는 빈 값일 수 없습니다")
    private String phoneNumber;

    @Override
    public Payment toEntity() {
        PhonePayment phonePayment = PhonePayment.builder()
                .phoneNumber(phoneNumber)
                .build();
        return phonePayment;
    }
}
