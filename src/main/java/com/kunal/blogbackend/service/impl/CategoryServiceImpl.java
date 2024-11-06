package com.kunal.blogbackend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.kunal.blogbackend.dto.CategoryDto;
import com.kunal.blogbackend.entity.Category;
import com.kunal.blogbackend.exception.ResourceFoundException;
import com.kunal.blogbackend.exception.ResourceNotFoundException;
import com.kunal.blogbackend.repository.CategoryRepository;
import com.kunal.blogbackend.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository,ModelMapper modelMapper){
        this.categoryRepository=categoryRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        boolean isCategoryExist=categoryRepository.existsByTitle(categoryDto.getTitle());
        if(isCategoryExist) throw new ResourceFoundException("Category", "Id", categoryDto.getCId());
        Category category=modelMapper.map(categoryDto,Category.class);
        Category savedCategory=categoryRepository.save(category);
        return modelMapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(long cId, CategoryDto categoryDto) {
        Category category=categoryRepository.findById(cId).orElseThrow(()->new ResourceNotFoundException("Category","Id",cId));
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        Category updatedCategory=categoryRepository.save(category);
        return modelMapper.map(updatedCategory, CategoryDto.class);
        
    }

    @Override
    public CategoryDto getCategoryById(long cId) {
       Category foundCategory=categoryRepository.findById(cId).orElseThrow(()->new ResourceNotFoundException("Category", "Id",cId));
       return modelMapper.map(foundCategory, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories=categoryRepository.findAll();
        return categories.stream().map(category->modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(long id) {
        Category category=categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","Id", id));
        categoryRepository.delete(category);
    }

}
