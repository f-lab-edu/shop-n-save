package com.flab.demo.domain;

import lombok.*;

import java.sql.Timestamp;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long id;
    private String productName;
    private int fixedPrice;
    private Long sellerId;
    private String salesYn;
    private Timestamp createDate;
}
