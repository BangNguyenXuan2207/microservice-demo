package com.example.cloudgatway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBack {

    @GetMapping("categoryServiceFallBack")
    public String categoryServiceFallBack (){
        return  "He thong Category-Service dang bi loi. Ban co the tro lai sau";
    }

    @GetMapping("productServiceFallBack")
    public String productServiceFallBack (){
        return  "He thong Product-Service dang bi loi. Ban co the tro lai sau";
    }
}
