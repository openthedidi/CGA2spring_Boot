package com.cj.cga101g1.util.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**設定Interceptor的bean**/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private MyInterceptor myInterceptor;
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/ExceptionHandlDemo2");

        /**
         * 啟動過濾的url
         */
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/mem/getMemByMemEmail")
                .addPathPatterns("/mem/MemEditServlet/**")
                .addPathPatterns("/users/**");
    }
}
