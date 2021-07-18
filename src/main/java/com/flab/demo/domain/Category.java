package com.flab.demo.domain;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
public class Category {

    private Integer id;
    private Integer parentId;
    private String categoryName;
    private Timestamp createDate;
}
