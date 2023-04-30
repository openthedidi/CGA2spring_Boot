package com.cj.cga101g1.util;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("CGA101G1")
public class ThymeleafController {
    @GetMapping("/home")
    public String home(){
        return "home";
    }
    @GetMapping("/backend/Test")
    public String test(){
        return "CGA101G1/backend/Test";
    }
}
