package com.cj.cga101g1.member.service;

import com.cj.cga101g1.member.dao.MemberDao;
import com.cj.cga101g1.member.util.Mem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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
    public Mem getMemByMemAccountAndPassword(String Account, String password) {
        return memberDao.getMemByMemAccountAndPassword(Account,password);
    }

    @Override
    public Mem memEdit(Integer memNo,Mem mem) {
        Mem memResult = memberDao.memEdit(mem);
        return memberDao.memEdit(memResult);
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

    @Override
    public Mem getMemByMemAEmail(String email) {
      return memberDao.getMemByMemEmail(email);
    }

    @Override
    public Map getShoppingMemInfo(String memAccount) {
        Map<String,Object> map = new HashMap<>();
        Mem mem = memberDao.getMemByMemAccount(memAccount);
        map.put("memNo", mem.getMemNo());
        map.put("creditcardDate", mem.getCreditcardDate());
        map.put("creditcardNo", mem.getCreditcardNo());
        map.put("City", mem.getMemCity());
        map.put("Dist", mem.getMemDist());
        map.put("Add", mem.getMemAdd());
        map.put("memMobile", mem.getMemMobile());
        map.put("memName", mem.getMemName());
        return map;
    }
}
