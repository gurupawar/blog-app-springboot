package com.guru.blogapp.services;

import java.util.List;

import com.guru.blogapp.payloads.CategoryDto;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);

    void deleteCategory(Long categoryId);

    CategoryDto getCategory(Long categoryId);

    List<CategoryDto> getCategories();
}
