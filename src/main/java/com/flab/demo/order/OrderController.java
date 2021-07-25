package com.flab.demo.order;

import com.flab.demo.annotation.Authority;
import com.flab.demo.annotation.LoginMember;
import com.flab.demo.domain.AuthMember;
import com.flab.demo.enums.Role;
import com.flab.demo.order.dto.CreateOrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class OrderController {

    //@Authority(target = {Role.BASIC_MEMBER})
    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrder(@Valid @RequestBody final CreateOrderRequestDto createOrderRequestDto/*, @LoginMember AuthMember authMember*/) {
        // 주문 생성
        System.out.println("주문 생성");
    }
}
