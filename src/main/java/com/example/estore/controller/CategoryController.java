    package com.example.estore.controller;

import com.example.estore.entity.Category;
import com.example.estore.entity.Producer;
import com.example.estore.repository.CategoryRepository;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
public class CategoryController {
    @Autowired
    public CategoryRepository categoryRepository;
    RestTemplate restTemplate = new RestTemplate();
    @GetMapping("/getAllCategories")
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
    @GetMapping("categories/{id}")
    public Category findCategoryById(@PathVariable int id){
        return categoryRepository.findById(id).get();
    }
    
    @GetMapping("/getAllProducer")
    @Retry(name = "producerServer")
    public List<Producer> getAll()
    {
        List<Producer> list = 
        restTemplate.exchange("http://localhost:5666/getAllProducer",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Producer>>() {}
        ).getBody();
        return list;
    }
    
    

}
