package com.flab.demo.domain;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Builder
@Getter
public class Product {

    private Long id;
    private String productName;
    private int fixedPrice;
    private Long sellerId;
    private String salesYn;
    private Timestamp createDate;
}
