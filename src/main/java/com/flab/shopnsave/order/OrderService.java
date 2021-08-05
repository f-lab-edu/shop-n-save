package com.flab.shopnsave.order;

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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;

    private final ProductService productService;

    @Transactional
    public void placeOrder(CreateOrderRequestDto createOrderRequestDto, AuthMember authMember) {
        Order order = createOrderRequestDto.toEntity(authMember);
        orderMapper.createOrder(order);

        // 주문 항목 리스트 내 상품 id 리스트 추출
        List<Long> productIdList = createOrderRequestDto.getOrderProductRequestDtoList().stream().map(
                CreateOrderProductRequestDto::getProductId).collect(Collectors.toList());

        Map<Long, Product> productById = productService.getByIdList(productIdList);

        List<OrderProduct> orderProducts = createOrderRequestDto.getOrderProductRequestDtoList().stream().map(
                orderProductRequestDto ->
                        orderProductRequestDto.toEntity(order.getId(), productById.get(orderProductRequestDto.getProductId()).getFixedPrice()))
                .collect(Collectors.toList());

        orderMapper.createOrderProducts(orderProducts);
    }

    public Order getById(long id) {
        return orderMapper.getById(id).orElseThrow(NotFoundOrderException::new);
    }
}
