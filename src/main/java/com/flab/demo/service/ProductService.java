package com.flab.demo.service;

import com.flab.demo.domain.Member;
import com.flab.demo.dto.product.CreateProductRequestDto;
import com.flab.demo.mapper.ProductMapper;
import com.flab.demo.system.Authentification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final Authentification authentification;
    private final MemberService memberService;

    public void createProduct(CreateProductRequestDto createProductRequestDto) {
        Member member = memberService.getByEmail(authentification.getLoginMemberEmail());

        productMapper.create(createProductRequestDto.toEntity(), member.getId().toString());
    }
}
