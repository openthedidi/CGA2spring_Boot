package com.cj.cga101g1.product.controller;

import com.cj.cga101g1.product.model.Product;
import com.cj.cga101g1.product.service.Method01;
import com.cj.cga101g1.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Validated
public class ProductController {

    @Autowired
    private Method01 method01;


    @PostMapping("/newMethod")
    public Method01 newMethod(@RequestBody Method01 method01){
        return  method01;
    }







    @Autowired
    private ProductService productService;

    @GetMapping("/products/{productNo}")//直接填入參數
    public ResponseEntity<Product> getProduct(@PathVariable Integer productNo){
       Product product = productService.findByPrimaryKey(productNo);

       if(product!=null){
           return ResponseEntity.status(HttpStatus.OK).body(product);
       }else {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
    }





    @GetMapping("/products")//從requst的名字取值
    public ResponseEntity<Product> getProductByParaName(@RequestParam(name="productNo") Integer productNo){
        Product product = productService.findByPrimaryKey(productNo);

        if(product!=null){
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}