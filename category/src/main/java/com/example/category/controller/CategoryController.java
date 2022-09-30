package com.example.category.controller;

import com.example.category.entity.Category;
import com.example.category.model.CategoryDTO;
import com.example.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("categorys")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public Category saveCategory(@RequestBody Category category){
    	
        return  categoryService.saveCategory(category);
    }

    @GetMapping("/{id}")
    public Category findByIdCategory(@PathVariable Long id){

    	return  categoryService.findByCategoryId(id);
    }
    
    @GetMapping
    public List<Category> listCategory(){
    	
    	return categoryService.findAllCategory();
    }
    
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id,  @RequestBody CategoryDTO dto) {
    	
    	Category cate = categoryService.findByCategoryId(id);
    	
    	cate.setName(dto.getName());
    	
    	return categoryService.update(cate);
    	
    	
    }

    @DeleteMapping("/{id}")
    public String deleteCategory (@PathVariable Long id ) {
    	Category category = categoryService.findByCategoryId(id);
    	
    	return categoryService.deleteCategory(category);
    }
    
    @GetMapping("/search")
    public List<Category> searchByCategoryName(@RequestParam(defaultValue = "") String name){
    	
    	return categoryService.searchByCategoryName(name);
    }
    
    @GetMapping("/paginate")
    public Page<Category> findPageBy(@RequestParam(defaultValue = "name") String sort,
    		                         @RequestParam(defaultValue = "0") Integer page,
    		                         @RequestParam(defaultValue = "10") Integer size, 
    		                         @RequestParam(defaultValue = "") String name){
    	
    	Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.ASC, sort));
    	return categoryService.findPageBy(name, pageable);
    }
}
