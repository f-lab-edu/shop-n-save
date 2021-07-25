package com.flab.demo.category.dto;

import com.flab.demo.category.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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