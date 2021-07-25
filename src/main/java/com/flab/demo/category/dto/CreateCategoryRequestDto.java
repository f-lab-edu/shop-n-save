package com.flab.demo.category.dto;

import com.flab.demo.category.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCategoryRequestDto {

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