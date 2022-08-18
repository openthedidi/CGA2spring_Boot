package com.cj.cga101g1.product.controller;

import com.cj.cga101g1.product.model.Product;
import com.cj.cga101g1.product.service.Method01;
import com.cj.cga101g1.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController("/CGA101G1/product")
@Validated
public class ProductController {

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
