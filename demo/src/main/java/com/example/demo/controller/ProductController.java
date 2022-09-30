package com.example.demo.controller;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.DTO.ResponseTemplate;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jdk.jfr.Category;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    ProductService productService;
    
    @GetMapping
    public List<Product> listProduct (){
    	
    	return productService.findAllProduct();
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product  product){
    	
        return   productService.saveProduct(product);

    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id){
        
        return  productService.findById(id);
    }



    @GetMapping("/{id}/categorys")

    public ResponseTemplate getProductAndCategory(@PathVariable Long id){

        return productService.getProuctAndCategory(id);

    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductDTO dto ) {
    	
    	Product product = productService.findById(id);
    	
    	BeanUtils.copyProperties(dto, product);
    	
    	return productService.update(product);
    }
    
    @DeleteMapping("/{id}")
    public String daleteProduct(@PathVariable Long id ) {
    	
    	return productService.deleteProductById(id);
    }
    
    @GetMapping("/search")
    public List<Product> findByName(@RequestParam(defaultValue = "") String name){
    	
    	return productService.findByName(name);
    }
    
    @GetMapping("/paginate")
    public Page<Product> findPageBy(@RequestParam(defaultValue = "name") String sort,
    		                         @RequestParam(defaultValue = "0") Integer page,
    		                         @RequestParam(defaultValue = "10") Integer size, 
    		                         @RequestParam(defaultValue = "") String name){
    	
    	Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.ASC, sort));
    	
    	return productService.findPageByName(name, pageable);
    }
}
