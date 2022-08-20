package com.cj.cga101g1.util;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/CGA101G1/frontend/Product/HomePageinshop.html")
    public String HomePageinshop(){
        return "/CGA101G1/frontend/Product/HomePageinshop";
    }

    @GetMapping("/CGA101G1/frontend/Product/HomePageinProduct.html")
    public String HomePageinProduct(){
        return "/CGA101G1/frontend/Product/HomePageinProduct";
    }
}
