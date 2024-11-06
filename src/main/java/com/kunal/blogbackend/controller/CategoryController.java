package com.kunal.blogbackend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kunal.blogbackend.dto.CategoryDto;
import com.kunal.blogbackend.service.CategoryService;
import com.kunal.blogbackend.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    
    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    // create-category
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto ){
        CategoryDto savedCategory =categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(savedCategory,HttpStatus.CREATED);
    }

    // update-category
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(long id,@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto updateCategoryDto=categoryService.updateCategory(id, categoryDto);
        return new ResponseEntity<>(updateCategoryDto,HttpStatus.CREATED);
    }

    // get-categoryById
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") long id){
        CategoryDto foundCategory=categoryService.getCategoryById(id);
        return new ResponseEntity<>(foundCategory,HttpStatus.FOUND);
    }

    // getAll-categories
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> categories=categoryService.getAllCategory();
        return new ResponseEntity<>(categories,HttpStatus.FOUND);
    }

    // delete-category
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable ("id") long id){
        categoryService.deleteCategory(id);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("Category deleted sucessfully");
        apiResponse.setSuccess(true);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

}
