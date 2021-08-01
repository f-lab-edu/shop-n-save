package com.flab.demo.order;

import com.flab.demo.annotation.LoginMember;
import com.flab.demo.domain.AuthMember;
import com.flab.demo.order.dto.CreateOrderProductRequestDto;
import com.flab.demo.order.dto.CreateOrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    public void placeOrder(CreateOrderRequestDto createOrderRequestDto, @LoginMember AuthMember authMember) {
        List<CreateOrderProductRequestDto> orderProductRequestDtoList = createOrderRequestDto.getOrderProductRequestDtoList();

    }
}
