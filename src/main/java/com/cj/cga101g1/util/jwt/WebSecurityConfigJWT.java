package com.cj.cga101g1.util.jwt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigJWT extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationSuccessHandler jwtAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler jwtAuthenticationFailHandler;


    //設置BCrypt密碼編輯器
    @Bean
    public PasswordEncoder passwordEncoder3() {
        return new BCryptPasswordEncoder();   // 使用 BCrypt 加密
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("啟動WebSecurityConfigJWT的configure");
        http.antMatcher("/jwt/**").
                //指定登录认证的Controller
                        formLogin().usernameParameter("memAccount").passwordParameter("memPassword").loginPage("/jwt/login").successHandler(
                        jwtAuthenticationSuccessHandler).failureHandler(jwtAuthenticationFailHandler)
                .and()
                .authorizeRequests()
                //登录相关
                .antMatchers("/register/mobile").permitAll()
                .antMatchers("/article/**").authenticated()
                .antMatchers("/tasks/**").hasRole("USER")
                //.antMatchers(HttpMethod.POST, "/jwt/tasks/**").hasRole("USER")
                .and()//.addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()));


        http.logout().permitAll();

        http.cors().and().csrf().ignoringAntMatchers("/jwt/**");

    }

    @Bean
    UserDetailsService JwtUserSecurityService() {
        return new JwtUserSecurityService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(JwtUserSecurityService()).passwordEncoder(new BCryptPasswordEncoder() {
        });
    }
}
