package com.example.demo.service;

import com.example.demo.DTO.Category;
import com.example.demo.DTO.ResponseTemplate;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        return null;
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
}
