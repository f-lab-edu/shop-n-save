package com.flab.shopnsave.category.dto;

import com.flab.shopnsave.category.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    public Optional<Integer> getUpdatableParentId() {
        return Optional.ofNullable(parentId);
    }

    public Optional<String> getUpdatableCategoryName() {
        return Optional.ofNullable(categoryName);
    }
}