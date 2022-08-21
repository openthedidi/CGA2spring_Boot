package com.cj.cga101g1.member.service;

import com.cj.cga101g1.member.util.Mem;

public interface MemberService {
    /** 註冊新會員 **/
    Mem newMember(Mem mem);

    /** 查會員byEmail **/
    Mem getMemByMemEmail(String memEmail);

    /** 更新會員資料  **/
    Mem memEdit(Integer memNo,Mem mem);
}
