package com.cj.cga101g1.member.dao;

import com.cj.cga101g1.member.util.Mem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MemberDaoImp implements MemberDao{
    @Autowired
    private Mem mem;
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Mem getMemByMemNo(Integer memNo) {
        mem = memberRepository.findById(memNo).orElse(null);
        return mem;
    }

    @Override
    public Mem getMemByMemEmail(String memEmail) {
        mem = memberRepository.findByMemEmail(memEmail);
        return mem;
    }

    @Transactional
    @Override
    public Mem memEdit(Mem mem) {
        memberRepository.editMem(mem.getMemName(),mem.getMemMobile(),mem.getMemEmail(),mem.getMemNo(), mem.getMemCity(),
                mem.getMemDist(),mem.getMemAdd());
        Mem memResult = memberRepository.findById(mem.getMemNo()).orElse(null);
        return memResult;
    }

    @Override
    public Mem login(Mem mem) {
        return memberRepository.findByAccountAndPassword(mem.getMemAccount(),mem.getMemPassword());
    }

    @Override
    public Mem getMemByMemAccount(String account) {
        return memberRepository.findByAccount(account);
    }

    @Override
    public Mem getMemByMemAccountAndPassword(String account, String password) {
        return memberRepository.findByAccountAndPassword(account,password);
    }

    @Override
    public Mem getMemSelfInfo(Mem mem) {
        return memberRepository.getMemSelfInfo(mem.getMemNo());
    }

    @Override
    public byte[] showMemSelfPic(Integer memNo) {
        return memberRepository.findMemPicByMemNo(memNo);
    }

    @Override
    public String getMemAccountByMemNo(Integer memNo) {
        return memberRepository.getMemAccountByMemNo(memNo);
    }

    @Transactional
    @Override
    public Mem newMem(Mem mem) {
        mem.setUserStatus(1);
        mem.setMemStatus(1);
        mem.setMemVrfed(1);
        Mem memResult =memberRepository.save(mem);
        int memNo = memResult.getMemNo();
        System.out.println(memNo+"新註冊ID");
        return memberRepository.findById(memNo).orElse(null);
    }
}
