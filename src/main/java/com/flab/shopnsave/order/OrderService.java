package com.flab.shopnsave.order;

import com.flab.shopnsave.annotation.LoginMember;
import com.flab.shopnsave.member.domain.AuthMember;
import com.flab.shopnsave.order.domain.Order;
import com.flab.shopnsave.order.domain.OrderProduct;
import com.flab.shopnsave.order.dto.CreateOrderRequestDto;
import com.flab.shopnsave.order.exception.NotFoundOrderException;
import com.flab.shopnsave.order.mapper.OrderMapper;
import com.flab.shopnsave.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;

    private final ProductService productService;

    @Transactional
    public void placeOrder(CreateOrderRequestDto createOrderRequestDto, @LoginMember AuthMember authMember) {
        Order order = createOrderRequestDto.toEntity(authMember);
        orderMapper.createOrder(order);

        List<OrderProduct> orderProducts = createOrderRequestDto.getOrderProductRequestDtoList().stream().map(
                orderProductRequestDto ->
                        orderProductRequestDto.toEntity(order.getId(), productService.getById(orderProductRequestDto.getProductId()).getFixedPrice()))
                .collect(Collectors.toList());

        orderMapper.createOrderProducts(orderProducts);
    }

    public Order getById(long id) {
        return orderMapper.getById(id).orElseThrow(NotFoundOrderException::new);
    }
}
