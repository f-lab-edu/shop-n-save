package com.flab.shopnsave.payment;

import com.flab.shopnsave.annotation.Authority;
import com.flab.shopnsave.annotation.LoginMember;
import com.flab.shopnsave.enums.Role;
import com.flab.shopnsave.member.domain.AuthMember;
import com.flab.shopnsave.payment.dto.CardPaymentRequestDto;
import com.flab.shopnsave.payment.dto.PhonePaymentRequestDto;
import com.flab.shopnsave.payment.service.CardPaymentService;
import com.flab.shopnsave.payment.service.PhonePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final CardPaymentService cardPaymentService;
    private final PhonePaymentService phonePaymentService;

    @Authority(target = {Role.BASIC_MEMBER})
    @PostMapping("/payments/card")
    @ResponseStatus(HttpStatus.CREATED)
    public void payCard(@Valid @RequestBody final CardPaymentRequestDto paymentRequestDto, @LoginMember AuthMember authMember) {
        cardPaymentService.pay(paymentRequestDto, authMember);
    }

    @Authority(target = {Role.BASIC_MEMBER})
    @PostMapping("/payments/phone")
    @ResponseStatus(HttpStatus.CREATED)
    public void payPhone(@Valid @RequestBody final PhonePaymentRequestDto paymentRequestDto, @LoginMember AuthMember authMember) {
        phonePaymentService.pay(paymentRequestDto, authMember);
    }
}