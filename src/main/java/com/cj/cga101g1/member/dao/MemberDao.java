package com.cj.cga101g1.member.dao;

import com.cj.cga101g1.member.util.Mem;

public interface MemberDao {
    Mem getMemByMemNo(Integer memNo);
    Mem newMem(Mem mem);
    Mem getMemByMemEmail(String memEmail);
    Mem memEdit(Mem mem);
}
