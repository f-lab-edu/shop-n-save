package com.flab.demo.service;

import com.flab.demo.domain.AuthMember;
import com.flab.demo.domain.Member;
import com.flab.demo.domain.Product;
import com.flab.demo.dto.product.CreateProductRequestDto;
import com.flab.demo.dto.product.ModifyProductRequestDto;
import com.flab.demo.enums.Role;
import com.flab.demo.exception.member.ForbiddenException;
import com.flab.demo.exception.member.NotFoundMemberException;
import com.flab.demo.exception.product.NotFoundProductException;
import com.flab.demo.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    public void createProduct(CreateProductRequestDto createProductRequestDto, String sellerId) {
        productMapper.create(createProductRequestDto.toEntity(), sellerId);
    }

    @Cacheable(value="product")
    public Product getById(String id) {
        return productMapper.getById(id).orElseThrow(NotFoundProductException::new);
    }

    @CachePut(value="product")
    public void modifyProduct(String id, ModifyProductRequestDto modifyProductRequestDto, AuthMember authMember) {
       Product product = productMapper.getById(id).orElseThrow(NotFoundProductException::new);
       if((authMember.getRole() != Role.ADMIN) && !authMember.getId().equals(product.getSellerId())) {
           throw new ForbiddenException();
       }
        productMapper.modifyProduct(id, modifyProductRequestDto.toEntity());
    }
}
