package com.cj.cga101g1.util.jwt;


import com.cj.cga101g1.member.service.MemberService;
import com.cj.cga101g1.member.util.Mem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

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

    public UserDetails loadUserByUserPassword(String account ,String password)  throws UsernameNotFoundException {
        Mem mem = memberService.getMemByMemAccountAndPassword(account,password);

        if (mem == null) {
            throw new UsernameNotFoundException("用戶名稱或是密碼錯誤");
        }
       /* else if("locked".equals(user.getStatus())) { //被锁定，无法登录
            throw new LockedException("用户被锁定");
        }*/
        return mem;
    }
}
