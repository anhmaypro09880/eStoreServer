package com.example.estore.controller;

import com.example.estore.entity.Category;
import com.example.estore.entity.Product;
import com.example.estore.repository.CategoryRepository;
import com.example.estore.repository.ProductRepository;
import com.example.estore.service.CookieService;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.client.RestClientException;
@RestController
@CrossOrigin
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    HttpServletResponse response;
    @Autowired
    public CategoryRepository categoryRepository;
    @Autowired
    CookieService cookie;
    int count =1;
    RestTemplate restTemplate = new RestTemplate();
    @GetMapping("/getAllProduct")
    @Retry(name = "productServer",fallbackMethod = "getDefaultProductRating")
    
    public List<Product> getAllProduct(){
//        try {
//            System.out.println("call retry lan " +count);
//            count++;
//            restTemplate.getForObject("",Product.class);
//        } catch (RestClientException e) {
//            System.out.println("call retry lan "+count);
//        }
        
        
        return productRepository.findAll();
    }
    public List<Product> getDefaultProductRating( Exception ex) {
        count=1;
        return new ArrayList<>();
    }
    @GetMapping("product/{id}")
    public Product findCategoryById(@PathVariable int id){
        return productRepository.findById(id).get();
    }
    @GetMapping("list-by-category/{cid}")
    public List<Product> listByCategory(@PathVariable int cid){
        Category cate = categoryRepository.findById(cid).get();
        List<Product> list = cate.getProducts();
        return list;
    }

}
