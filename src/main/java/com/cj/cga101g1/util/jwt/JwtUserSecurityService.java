package com.cj.cga101g1.util.jwt;


import com.cj.cga101g1.member.service.MemberService;
import com.cj.cga101g1.member.util.Mem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUserSecurityService implements UserDetailsService {
    @Autowired
    private MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String account)  throws UsernameNotFoundException {
        Mem mem = memberService.getMemByMemAccount(account);

        if (mem == null) {
            throw new UsernameNotFoundException("用户名邮箱手机号不存在!");
        }
       /* else if("locked".equals(user.getStatus())) { //被锁定，无法登录
            throw new LockedException("用户被锁定");
        }*/
        return mem;
    }


    /**
     * 此處創造 loadUserByUserPassword 方法，
     * 其 account 參數 與 password參數，會傳入在登入畫面所輸入的帳號與密碼。
     * 我們從資料庫找出對應的使用者後，使用它的帳號（電子郵件）與密碼建立 Mem 物件，
     * 並回傳給驗證機制使用。
     * */
    public UserDetails loadUserByUserPassword(String account ,String password)  throws UsernameNotFoundException {
        System.out.println("啟動loadUserByUserPassword 驗證帳號與密碼");
        Mem mem = memberService.getMemByMemAccountAndPassword(account,password);
        if (mem == null) {
            throw new UsernameNotFoundException("用戶名稱或是密碼錯誤");
        }
        return mem;
    }
}
