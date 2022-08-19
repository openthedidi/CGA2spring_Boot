package com.cj.cga101g1.productPic.controller;

import com.cj.cga101g1.productPic.model.ProductPic;
import com.cj.cga101g1.productPic.service.ProductPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("CGA101G1/product")
public class ProductPicController {
    @Autowired
    private ProductPic productPic;
    @Autowired
    private ProductPicService productPicService;


    @GetMapping(value = "/showOneCover",
            produces = MediaType.IMAGE_GIF_VALUE)
    public @ResponseBody byte[] showOneCover(@RequestParam Integer ProductNO){
        productPic= productPicService.getOneCoverByProductNo(ProductNO);
        return productPic.getProductPicContent();
    };

    @GetMapping(value = "/showOneCoverByPicNO",
    produces = MediaType.IMAGE_GIF_VALUE)
    public @ResponseBody byte[] showOneCoverByPicNO(@RequestParam Integer ProductPicNO){
        productPic= productPicService.getOneCoverByProductPicNo(ProductPicNO);
        return productPic.getProductPicContent();
    };


}
