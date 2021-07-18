package com.flab.demo.dto.category;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CategoryRequestDto {

    private int parentId;

    @NotBlank
    private String categoryName;
}