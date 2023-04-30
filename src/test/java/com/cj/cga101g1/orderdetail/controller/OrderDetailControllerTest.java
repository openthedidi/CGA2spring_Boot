package com.cj.cga101g1.orderdetail.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderDetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void comments() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/CGA101G1/orderDetail/comments?productNo=21001");
        MvcResult mvcResult =  mockMvc.perform(requestBuilder)
                .andExpectAll(status().is(200))
                .andReturn();
        assertEquals("application/json",mvcResult.getResponse().getContentType());
    }
}