package com.cj.cga101g1.member.service;

import com.cj.cga101g1.member.util.Mem;

import java.util.Map;

public interface MemberService {
    /** 註冊新會員 **/
    Mem newMember(Mem mem);

    /** 查會員byEmail **/
    Mem getMemByMemEmail(String memEmail);

    /**
     * 查會員藉由帳號
     * **/
    Mem getMemByMemAccount(String Account);

    /**
     * 查會員藉由帳號與密碼
     * **/
    Mem getMemByMemAccountAndPassword(String Account, String password);

    /** 更新會員資料  **/
    Mem memEdit(Integer memNo,Mem mem);

    /** 登入 **/
    Mem login(Mem mem);

    /**  展示會員個人資料  **/
    Mem getMemSelfInfo(Mem memVO);
    /**  展示會員個人頭貼  **/
    byte[] showMemSelfPic(Integer memNo);

    Mem getMemByMemAEmail(String email);

    Map getShoppingMemInfo(String memAccount);
}
