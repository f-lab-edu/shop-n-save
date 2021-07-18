package com.flab.shopnsave.category.dto;

import com.flab.shopnsave.category.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CreateCategoryRequestDto {

    private Integer parentId;

    private int depth;

    @NotBlank
    private String categoryName;

    public Category toEntity() {
        Category category = Category.builder()
                .parentId(this.parentId)
                .categoryName(this.categoryName)
                .depth(0)
                .build();
        return category;
    }

    public Category toEntityWithDepth(int depth) {
        Category category = Category.builder()
                .parentId(this.parentId)
                .categoryName(this.categoryName)
                .depth(depth)
                .build();
        return category;
    }
}