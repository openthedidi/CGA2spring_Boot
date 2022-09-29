package com.cj.cga101g1.util.exceptionHandler;


import javax.servlet.http.HttpServletRequest;

public class FindRequestURL {
    public String getURL(HttpServletRequest httpServletRequest){
        String schema = httpServletRequest.getRequestURI();
        return schema;
    };
}
