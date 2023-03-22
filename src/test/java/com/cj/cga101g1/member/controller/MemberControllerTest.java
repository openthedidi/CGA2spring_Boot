package com.cj.cga101g1.member.controller;

import com.cj.cga101g1.member.util.Mem;
import org.apache.coyote.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Transactional
    @Test
    void getMemSelfInfo() throws Exception {
        Mem mem =new Mem();
        mem.setMemNo(11002);
        MockHttpSession mockHttpSession =new MockHttpSession();
        mockHttpSession.setAttribute("memVO",mem);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/mem/MemSelfInfo")
                .session(mockHttpSession);
//        RequestBuilder requestBuilder= MockMvcRequestBuilders.put("/MemSelfInfo")
//                .session(mockHttpSession);
        MvcResult mvcResult = mockMvc.perform(builder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();

    }
}