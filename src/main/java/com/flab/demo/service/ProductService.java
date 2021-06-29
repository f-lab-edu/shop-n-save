package com.flab.demo.service;

import com.flab.demo.dto.product.CreateProductRequestDto;
import com.flab.demo.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    public void createProduct(CreateProductRequestDto createProductRequestDto, String sellerId) {
        productMapper.create(createProductRequestDto.toEntity(), sellerId);
    }
}
