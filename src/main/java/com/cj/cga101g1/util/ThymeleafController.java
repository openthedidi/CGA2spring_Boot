package com.cj.cga101g1.util;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/Product/HomePageinshop")
    public String HomePageinshop(){
        return "/Product/HomePageinshop";
    }
}
