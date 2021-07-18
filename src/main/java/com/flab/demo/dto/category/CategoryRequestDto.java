package com.flab.demo.dto.category;

import com.flab.demo.domain.Category;
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