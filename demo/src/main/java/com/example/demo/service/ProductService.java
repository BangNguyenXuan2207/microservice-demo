package com.example.demo.service;

import com.example.demo.DTO.Category;
import com.example.demo.DTO.ResponseTemplate;
import com.example.demo.entity.Product;
import com.example.demo.exception.ProductException;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    RestTemplate restTemplate;
    
   

    public Product saveProduct(Product product) {

        return productRepository.save(product);
    }

    public Product findById(Long id) {

        Optional<Product> opt = productRepository.findById(id);
        if (opt.isPresent()) {
            return  opt.get();
        }
       throw new ProductException("khong tim thay Product voi id: " + id);
    }

    public ResponseTemplate getProuctAndCategory(Long id){
        ResponseTemplate rest = new ResponseTemplate();
        Optional<Product> opt = productRepository.findById(id);
        if (opt.isPresent()){
            Product product = opt.get();
            Category category = restTemplate.getForObject("http://CATEGORY-SERVICE/category/" + product.getCategoryId(), Category.class);

            rest.setCategory(category);
            rest.setProduct(product);
        }
        return  rest;
    }

	public List<Product> findAllProduct() {
		
		return productRepository.findAll();
	}

	public Product update(Product product) {
		
		return productRepository.save(product);
	}


	public String deleteProductById(Long id) {

		Product product = this.findById(id);
		
		productRepository.delete(product);
		return "xoa thanh cong Product";
	}

	public List<Product> findByName(String name) {

		return productRepository.findByNameContaining(name);
	}
	
	public Page<Product> findPageByName(String name, Pageable pageable){
		
		return productRepository.findByNameContaining(name, pageable);
	}
}
