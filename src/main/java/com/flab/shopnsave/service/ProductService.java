package com.flab.shopnsave.service;

import com.flab.shopnsave.domain.Product;
import com.flab.shopnsave.dto.product.CreateProductRequestDto;
import com.flab.shopnsave.dto.product.ModifyProductRequestDto;
import com.flab.shopnsave.enums.Role;
import com.flab.shopnsave.exception.product.NotFoundProductException;
import com.flab.shopnsave.mapper.ProductMapper;
import com.flab.shopnsave.member.domain.AuthMember;
import com.flab.shopnsave.member.exception.ForbiddenException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    public void createProduct(CreateProductRequestDto createProductRequestDto, long sellerId) {
        productMapper.create(createProductRequestDto.toEntity(sellerId));
    }

    @Cacheable(value = "product", key = "#id", unless = "#result == null")
    public Product getById(long id) {
        return productMapper.getById(id).orElseThrow(NotFoundProductException::new);
    }

    @CacheEvict(value = "product", key = "#id")
    public void modifyProduct(long id, ModifyProductRequestDto modifyProductRequestDto, AuthMember authMember) {
        Product product = getById(id);
        if ((authMember.getRole() != Role.ADMIN) && !authMember.getId().equals(product.getSellerId())) {
            throw new ForbiddenException();
        }
        product.modifyProduct(modifyProductRequestDto);
        productMapper.modifyProduct(product);
    }

    public Map<Long, Product> getByIdList(List<Long> id) {
        return productMapper.getByIdList(id).stream().collect(Collectors.toMap(Product::getId, product -> product));
    }
}
