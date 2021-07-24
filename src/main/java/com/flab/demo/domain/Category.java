package com.flab.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    private Integer id;
    private Integer parentId;
    private String categoryName;
    private Timestamp createDate;

    public void changeCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void changeParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
