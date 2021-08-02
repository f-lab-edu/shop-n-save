package com.flab.shopnsave.order;

import com.flab.shopnsave.annotation.LoginMember;
import com.flab.shopnsave.domain.Product;
import com.flab.shopnsave.member.domain.AuthMember;
import com.flab.shopnsave.order.domain.Order;
import com.flab.shopnsave.order.domain.OrderProduct;
import com.flab.shopnsave.order.dto.CreateOrderProductRequestDto;
import com.flab.shopnsave.order.dto.CreateOrderRequestDto;
import com.flab.shopnsave.order.exception.NotFoundOrderException;
import com.flab.shopnsave.order.mapper.OrderMapper;
import com.flab.shopnsave.service.ProductService;
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
