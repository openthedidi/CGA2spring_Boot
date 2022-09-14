package com.cj.cga101g1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/* 改成war用 */
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
public class Cga101G1Application {

    public static void main(String[] args) {
        SpringApplication.run(Cga101G1Application.class, args);
    }

}
