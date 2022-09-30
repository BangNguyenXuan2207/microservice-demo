package com.example.category.service;

import com.example.category.entity.Category;
import com.example.category.exception.CategoryException;
import com.example.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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
        throw new CategoryException("khong tim thay category voi id " + id) ;
    }


	public List<Category> findAllCategory() {
		
		return categoryRepository.findAll();
	}


	public Category update(Category category) {
		
		return categoryRepository.save(category);
	}


	public String  deleteCategory(Category category) {
		
		 categoryRepository.delete(category);
		
		return "xoa thanh cong";
	}


	public List<Category> searchByCategoryName(String name) {
		
		return  categoryRepository.findByNameContaining(name);
	}


	public Page<Category> findPageBy(String name, Pageable pageable) {
		
		return categoryRepository.findByNameContaining(name, pageable);
	}
}
