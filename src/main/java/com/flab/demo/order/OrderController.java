package com.flab.demo.order;

import com.flab.demo.annotation.Authority;
import com.flab.demo.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    @Authority(target = {Role.BASIC_MEMBER})
    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrder() {

    }
}
