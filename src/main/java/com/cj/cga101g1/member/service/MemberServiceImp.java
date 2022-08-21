package com.cj.cga101g1.member.service;

import com.cj.cga101g1.member.dao.MemberDao;
import com.cj.cga101g1.member.util.Mem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImp implements MemberService{
    @Autowired
    private MemberDao memberDao;

    @Override
    public Mem newMember(Mem mem) {
        /***** 查詢有無重複會員名稱或是mail ****/
        Mem memQuery=memberDao.getMemByMemEmail(mem.getMemEmail());
        if(memQuery==null){
            Mem memResult = memberDao.newMem(mem);
            return memResult;
        }else{
            return null;
        }
    }

    @Override
    public Mem getMemByMemEmail(String memEmail) {
        return memberDao.getMemByMemEmail(memEmail);
    }

    @Override
    public Mem memEdit(Integer memNo,Mem mem) {
        Mem memQuery = memberDao.getMemByMemNo(memNo);
        return memberDao.memEdit(memQuery);
    }
}
