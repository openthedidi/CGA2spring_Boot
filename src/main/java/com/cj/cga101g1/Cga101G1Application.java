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

    /** 啟動放在靜態資源的html檔，
     * addResourceHandlers() 方法設置了一個靜態資源處理器。
     * 這個處理器將 /** 根目錄的路徑映射到 classpath:/static/ 目錄下的靜態資源。
     * 當瀏覽器請求 / 目錄下的靜態資源時，
     * Spring MVC 將會根據路徑映射將資源返回給瀏覽器。
     **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }

}
