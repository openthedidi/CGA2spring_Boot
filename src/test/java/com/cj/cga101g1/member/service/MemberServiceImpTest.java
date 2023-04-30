package com.cj.cga101g1.member.service;

import com.cj.cga101g1.member.dao.MemberDao;
import com.cj.cga101g1.member.util.Mem;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceImpTest {

    @Autowired
    private Mem memMock;
    @Autowired
    private MemberService memberService;
    @MockBean
    private MemberDao memberDao;

    @Test
    void getMemByMemAccount() {
        memMock.setMemNo(11001);
        Mockito.when(memberDao.getMemByMemAccount("bbb")).thenReturn(memMock);
        Mem resultMem = memberService.getMemByMemAccount("bbb");
        assertNotNull(resultMem);
        assertEquals(11001,resultMem.getMemNo());
    }
}