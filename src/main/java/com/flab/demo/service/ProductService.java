package com.flab.demo.service;

import com.flab.demo.domain.AuthMember;
import com.flab.demo.domain.Product;
import com.flab.demo.dto.product.CreateProductRequestDto;
import com.flab.demo.dto.product.ModifyProductRequestDto;
import com.flab.demo.enums.Role;
import com.flab.demo.exception.member.ForbiddenException;
import com.flab.demo.exception.product.NotFoundProductException;
import com.flab.demo.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    public void createProduct(CreateProductRequestDto createProductRequestDto, long sellerId) {
        productMapper.create(createProductRequestDto.toEntity(sellerId));
    }

    @Cacheable(value="product", key = "#id", unless="#result == null")
    public Product getById(long id) {
        return productMapper.getById(id).orElseThrow(NotFoundProductException::new);
    }

    @CacheEvict(value="product", key = "#id")
    public void modifyProduct(long id, ModifyProductRequestDto modifyProductRequestDto, AuthMember authMember) {
       Product product = getById(id);
       if((authMember.getRole() != Role.ADMIN) && !authMember.getId().equals(product.getSellerId())) {
           throw new ForbiddenException();
       }
       product.modifyProduct(modifyProductRequestDto);
       productMapper.modifyProduct(product);
    }
}
