package com.cj.cga101g1.member.service;

import com.cj.cga101g1.member.dao.MemberDao;
import com.cj.cga101g1.member.util.Mem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

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
    public Mem getMemByMemAccount(String Account) {
        return memberDao.getMemByMemAccount(Account);
    }

    @Override
    public Mem memEdit(Integer memNo,Mem mem) {
        Mem memQuery = memberDao.getMemByMemNo(memNo);
        return memberDao.memEdit(memQuery);
    }

    @Override
    public Mem login(Mem mem) {
        final String memAccount = mem.getMemAccount();
        final String memPassword = mem.getMemPassword();
        if (memAccount == null) {
            mem.setMessage("帳號未輸入");
            mem.setSuccessful(false);
            return mem;
        }

        if (memPassword == null) {
            mem.setMessage("密碼未輸入");
            mem.setSuccessful(false);
            return mem;
        }
        Mem resultMem = memberDao.login(mem);
        if (resultMem == null) {
            mem.setMessage("帳號或密碼錯誤");
            mem.setSuccessful(false);
            return mem;
        }
        resultMem.setMessage("登入成功");
        resultMem.setSuccessful(true);
        return memberDao.login(mem);
    }

    @Override
    public Mem getMemSelfInfo(Mem mem) {
        return memberDao.getMemSelfInfo(mem);
    }

    @Override
    public byte[] showMemSelfPic(Integer memNo) {
        byte[] result = memberDao.showMemSelfPic(memNo);
        if(result==null) {
            /** 預設的大頭照 **/
            return memberDao.showMemSelfPic(11000);
        }else{
            return memberDao.showMemSelfPic(memNo);
        }

    }
}
