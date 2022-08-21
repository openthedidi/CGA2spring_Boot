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
    public Mem newMem(Mem mem) {
        Mem memResult =memberRepository.save(mem);
        int memNo = memResult.getMemNo();
        System.out.println(memNo+"新註冊ID");
        return memberRepository.save(mem);
    }
}
