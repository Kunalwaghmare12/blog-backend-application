package com.kunal.blogbackend.service;

import java.util.List;

import com.kunal.blogbackend.dto.CategoryDto;

public interface CategoryService {
    
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(long cId,CategoryDto categoryDto);
    CategoryDto getCategoryById(long cId);
    List<CategoryDto> getAllCategory();
    void deleteCategory(long id);

}
