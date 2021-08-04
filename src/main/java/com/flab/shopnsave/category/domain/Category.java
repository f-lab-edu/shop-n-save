package com.flab.shopnsave.category.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    private Integer id;
    private Integer parentId;
    private int depth;
    private String categoryName;
    private Timestamp createdAt;

    @Builder
    public Category(Integer parentId, int depth, String categoryName) {
        Assert.notNull(categoryName, "카테고리 명이 존재하지않습니다");

        this.parentId = parentId;
        this.depth = depth;
        this.categoryName = categoryName;
    }

    public void changeCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void changeParentId(Integer parentId) {
        this.parentId = parentId;
    }
}