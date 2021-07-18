package com.flab.shopnsave.category.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Getter
@NoArgsConstructor
public class UpdateCategoryRequestDto {

    private Integer parentId;

    private String categoryName;

    public Optional<Integer> getUpdatableParentId() {
        return Optional.ofNullable(parentId);
    }

    public Optional<String> getUpdatableCategoryName() {
        return Optional.ofNullable(categoryName);
    }
}