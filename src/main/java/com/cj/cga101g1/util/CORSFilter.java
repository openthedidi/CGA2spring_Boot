package com.cj.cga101g1.util;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * CORS 是一個 W3C 標準，全稱是 " 跨域資源共享”（Cross-origin resource sharing），
 * 瀏覽器發出 CORS 非簡單請求，會在正式通信之前，增加一次 OPTIONS 查詢請求，稱爲 "預檢" 請求（preflight）。
 * 瀏覽器先詢問服務器，當前網頁所在的域名是否在服務器的許可名單之中，以及可以使用哪些 HTTP 動詞和頭信息字段。
 * 只有得到肯定答覆，瀏覽器纔會發出正式的 XMLHttpRequest 請求，否則就報錯。
 *
 * 參考來源
 * https://www.readfog.com/a/1634820519117295616
 *
 * **/
@WebFilter(filterName = "CorsFilter ")
@Configuration
public class CORSFilter implements Filter{
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        chain.doFilter(req, res);
    }
}
