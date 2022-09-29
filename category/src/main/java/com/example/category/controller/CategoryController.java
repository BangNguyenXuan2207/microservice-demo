package com.example.category.controller;

import com.example.category.entity.Category;
import com.example.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public Category saveCategory(@RequestBody Category category){
        return  categoryService.saveCategory(category);
    }

    @GetMapping("/{id}")
    public Category findByIdCategory(@PathVariable Long id){

       Category category = categoryService.findByCategoryId(id);
        return category;
    }

}
