package com.flab.shopnsave.category.dto;

import com.flab.shopnsave.category.domain.Category;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class CategoryRequestDto {

    private Integer parentId;

    @NotBlank
    private String categoryName;

    public Category toEntity() {
        Category category = Category.builder()
                .parentId(this.parentId)
                .categoryName(this.categoryName)
                .build();
        return category;
    }
}