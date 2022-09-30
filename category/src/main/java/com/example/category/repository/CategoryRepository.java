package com.example.category.repository;

import com.example.category.entity.Category;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

	List<Category> findByNameContaining(String name);
	
	
	Page<Category>  findByNameContaining(String name, Pageable pageable);


	
	
	
}


