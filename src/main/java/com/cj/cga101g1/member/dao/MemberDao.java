package com.cj.cga101g1.member.dao;

import com.cj.cga101g1.member.util.Mem;

public interface MemberDao {
    Mem getMemByMemNo(Integer memNo);
    Mem newMem(Mem mem);
    Mem getMemByMemEmail(String memEmail);
    Mem memEdit(Mem mem);
    Mem login(Mem mem);
    Mem getMemByMemAccount(String account);
    Mem getMemByMemAccountAndPassword(String account,String password);
    Mem getMemSelfInfo(Mem mem);
    byte[] showMemSelfPic(Integer memNo);
}
