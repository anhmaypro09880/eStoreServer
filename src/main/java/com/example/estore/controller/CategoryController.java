package com.example.estore.controller;

import com.example.estore.entity.Category;
import com.example.estore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @GetMapping("/getAllCategories")
    private List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
    @GetMapping("categories/{id}")
    private Category findCategoryById(@PathVariable int id){
        return categoryRepository.findById(id).get();
    }
}