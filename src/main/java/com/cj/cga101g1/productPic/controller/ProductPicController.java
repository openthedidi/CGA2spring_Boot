package com.cj.cga101g1.productPic.controller;

import com.cj.cga101g1.productPic.model.ProductPic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductPicController {
    @Autowired
    private ProductPic productPic;
}
