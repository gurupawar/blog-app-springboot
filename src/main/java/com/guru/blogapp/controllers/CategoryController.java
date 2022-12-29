package com.guru.blogapp.controllers;

import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.guru.blogapp.payloads.ApiResponse;
import com.guru.blogapp.payloads.CategoryDto;
import com.guru.blogapp.services.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    // Create Category
    // http://localhost:8080/api/category/
    @PostMapping("/")
    public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto createCate = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createCate, HttpStatus.CREATED);
    }

    // Update Category by id
    // http://localhost:8080/api/category/3
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
            @PathVariable Long categoryId) {

        CategoryDto updatedCate = this.categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<CategoryDto>(updatedCate, HttpStatus.CREATED);
    }

    // Delete category by id
    // http://localhost:8080/api/category/3
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted successfully", true),
                HttpStatus.OK);
    }

    // Get category by id
    // http://localhost:8080/api/category/3
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long categoryId) {
        CategoryDto cat = categoryService.getCategory(categoryId);
        return new ResponseEntity<CategoryDto>(cat, HttpStatus.OK);
    }

    // Get all categories
    // http://localhost:8080/api/category/
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> catList = categoryService.getCategories();
        return ResponseEntity.ok(catList);
    }
}
