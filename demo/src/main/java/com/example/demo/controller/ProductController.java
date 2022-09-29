package com.example.demo.controller;

import com.example.demo.DTO.ResponseTemplate;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public Product saveProduct(@RequestBody Product  product){
        return   productService.saveProduct(product);

    }


    public Product findById(@PathVariable Long id){
        Product product = productService.findById(id);
        return  product;
    }

//    private static  final  String SERVICE_A = "backendA";

    @GetMapping("/{id}")
//    @CircuitBreaker(name = SERVICE_A, fallbackMethod = "productServiceFallBack")
    public ResponseTemplate getProductAndCategory(@PathVariable Long id){

        return productService.getProuctAndCategory(id);

    }

//    public String productServiceFallBack (Exception e){
//        return  "He thong Product-Service dang bi loi. Ban co the tro lai sau";
//    }
}
