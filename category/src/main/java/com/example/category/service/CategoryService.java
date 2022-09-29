package com.example.category.service;

import com.example.category.entity.Category;
import com.example.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;


    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }


    public Category findByCategoryId(Long id) {
        Optional<Category> opt =  categoryRepository.findById(id);
        if (opt.isPresent()){
            return opt.get();
        }
        return null;
    }
}
