package com.cj.cga101g1.util.jwt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigJWT extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationSuccessHandler jwtAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler jwtAuthenticationFailHandler;
    @Autowired
    private UserDetailsService userDetailsService;

    //設置BCrypt密碼編輯器
    @Bean
    public PasswordEncoder passwordEncoder3() {
        return new BCryptPasswordEncoder();   // 使用 BCrypt 加密
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("啟動WebSecurityConfigJWT的configure");
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/CGA101G1/order/showAllOrderAndDetailsByMemNo").authenticated()
                .antMatchers(HttpMethod.GET,"/mem/jwt/MemSelfInfo").authenticated()
                .antMatchers("/jwt/login","**/assets/**","**/html").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/CGA101G1/frontend/memLogin/login.html").permitAll()
                .and()
                .addFilterBefore(new JWTAuthorizationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .logout()
                .permitAll();


//        http.antMatcher("/CGA101G1/frontend/mem/updateMemberData.html")
//                .formLogin()
//                .usernameParameter("memAccount")
//                .passwordParameter("memPassword")
//                .loginPage("/CGA101G1/frontend/memLogin/login.html")
//                .successHandler(
//                        jwtAuthenticationSuccessHandler).failureHandler(jwtAuthenticationFailHandler)
//                .and()
//                .authorizeRequests()
//                //登录相关
//                .antMatchers("/register/mobile").permitAll()
//                .antMatchers("/article/**").authenticated()
//                .antMatchers("/tasks/**").hasRole("USER")
//                .antMatchers(HttpMethod.POST, "/jwt/tasks/**").hasRole("USER")
//                .and()
//                .addFilter(new JWTAuthorizationFilter(authenticationManager()));


        http.logout().permitAll();

//        http.cors().and().csrf().ignoringAntMatchers("/jwt/**");

    }

    @Bean
    UserDetailsService JwtUserSecurityService() {
        return new JwtUserSecurityService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("呼叫WebSecurityConfigJWT - configure2進行使用者驗證");
        auth.userDetailsService(userDetailsService);

//        auth
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(new BCryptPasswordEncoder());
//                ↑↑↑↑
//                如果資料庫使用的密碼有加密，
//                使用BCryptPasswordEncoder與資料庫中使用者的已加密密碼進行比對。
    }
}
