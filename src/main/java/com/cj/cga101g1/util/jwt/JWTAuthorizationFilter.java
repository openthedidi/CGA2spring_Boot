package com.cj.cga101g1.util.jwt;

import com.cj.cga101g1.member.util.Mem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.security.auth.message.AuthException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }
    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        System.out.println("requestURL :" + request.getRequestURI());

        String tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println("doFilterInternal - tokenHeader:" + tokenHeader);
        //如果請求頭則中沒有授權信息直接放行了
        if (tokenHeader == null) {
            System.out.println("跳過驗證");
            chain.doFilter(request, response);
            return;
        }
        //如果請求頭有token，則進行解析，並且設置認證信息
        try {
            System.out.println("開始驗證by doFilterInternal");
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));

            System.out.println("header" + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
            System.out.println("header" + SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        } catch (AuthException e) {
            e.printStackTrace();
        }
        super.doFilterInternal(request, response, chain);
    }

    //這裡從token中獲取用戶信息並新建一個token
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) throws AuthException {
        System.out.println("啟動JWTAuthorizationFilter 的UsernamePasswordAuthenticationToken");
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String username = JwtTokenUtils.getUsername(token);
        System.out.println("username:" + username);
        String role = JwtTokenUtils.getUserRole(token);
        System.out.println("role: " + role );
        if (username != null){
            return new UsernamePasswordAuthenticationToken(username, null,
                    Collections.singleton(new SimpleGrantedAuthority(role))
            );
        }
        return null;
    }
}
