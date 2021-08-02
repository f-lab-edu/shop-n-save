package com.flab.demo.order;

import com.flab.demo.annotation.LoginMember;
import com.flab.demo.domain.AuthMember;
import com.flab.demo.domain.Product;
import com.flab.demo.order.domain.Order;
import com.flab.demo.order.domain.OrderProduct;
import com.flab.demo.order.dto.CreateOrderProductRequestDto;
import com.flab.demo.order.dto.CreateOrderRequestDto;
import com.flab.demo.order.exception.NotFoundOrderException;
import com.flab.demo.order.mapper.OrderMapper;
import com.flab.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;

    private final ProductService productService;

    @Transactional
    public void placeOrder(CreateOrderRequestDto createOrderRequestDto, @LoginMember AuthMember authMember) {
        Order order = createOrderRequestDto.toEntity(authMember);
        orderMapper.createOrder(order);

        List<OrderProduct> orderProducts = new ArrayList<>();
        List<CreateOrderProductRequestDto> orderProductRequestDtoList = createOrderRequestDto.getOrderProductRequestDtoList();
        for(CreateOrderProductRequestDto createOrderProductRequestDto : orderProductRequestDtoList) {
            Product product = productService.getById(createOrderProductRequestDto.getProductId());
            orderProducts.add(createOrderProductRequestDto.toEntity(order.getId(), product.getFixedPrice()));
        }
        orderMapper.createOrderProducts(orderProducts);
    }

    public Order getById(long id) {
        return orderMapper.getById(id).orElseThrow(NotFoundOrderException::new);
    }
}
