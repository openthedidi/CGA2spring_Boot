package com.cj.cga101g1.util.Interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class  LoginInterceptor implements HandlerInterceptor {

    /**
     *檢查HttpSession有無會員memVO這個key
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (request.getSession().getAttribute("memVO") == null) {
            response.setStatus(302);
            return false;
        }
        return true;
    }
}
