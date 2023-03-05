package com.cj.cga101g1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


///* 改成war用 */
//@SpringBootApplication
//public class Cga101G1Application extends SpringBootServletInitializer {
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(Cga101G1Application.class);
//    }
//
//    public static void main(String[] args) throws Exception {
//        SpringApplication.run(Cga101G1Application.class, args);
//    }
//
//}




@SpringBootApplication
public class Cga101G1Application implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(Cga101G1Application.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }

}
