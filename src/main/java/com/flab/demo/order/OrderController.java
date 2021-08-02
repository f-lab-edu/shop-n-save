package com.flab.demo.order;

import com.flab.demo.annotation.Authority;
import com.flab.demo.annotation.LoginMember;
import com.flab.demo.domain.AuthMember;
import com.flab.demo.enums.Role;
import com.flab.demo.order.domain.Order;
import com.flab.demo.order.dto.CreateOrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Authority(target = {Role.BASIC_MEMBER})
    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrder(@Valid @RequestBody final CreateOrderRequestDto createOrderRequestDto, @LoginMember AuthMember authMember) {
        orderService.placeOrder(createOrderRequestDto, authMember);
    }

    @Authority(target = {Role.BASIC_MEMBER})
    @GetMapping("/orders/{id}")
    public Order getById(@PathVariable("id") final int id, @LoginMember AuthMember authMember) {
        return orderService.getById(id);
    }
}
