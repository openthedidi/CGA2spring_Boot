package com.cj.cga101g1.util.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component("JwtAuthenticationFailHandler")
public class JwtAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {
    /**
     * 驗證錯誤後的處理
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException, ServletException, IOException {
        System.out.println("onAuthenticationFailure");
        httpServletRequest.setCharacterEncoding("UTF-8");
        // 從前端獲得錯誤的名稱及密碼
        String username = httpServletRequest.getParameter("memAccount");
        String password = httpServletRequest.getParameter("memPassword");

        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.write("{\"status\":\"error\",\"message\":\"用户名或密码错误\"}");
        out.flush();
        out.close();
    }
}
