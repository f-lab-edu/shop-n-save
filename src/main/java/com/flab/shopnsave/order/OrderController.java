package com.flab.shopnsave.order;

import com.flab.shopnsave.annotation.Authority;
import com.flab.shopnsave.annotation.LoginMember;
import com.flab.shopnsave.enums.Role;
import com.flab.shopnsave.member.domain.AuthMember;
import com.flab.shopnsave.order.domain.Order;
import com.flab.shopnsave.order.dto.CreateOrderRequestDto;
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
