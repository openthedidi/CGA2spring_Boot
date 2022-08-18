package com.cj.cga101g1.gameplatformtype.controller;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockReset;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class GamePlatformTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getOneType() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/gamePlatformType/getOneType/64001");

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.gamePlatformName",notNullValue()))
                .andExpect(jsonPath("$.gamePlatformName",equalTo("Switch")))
                .andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
//        System.out.println(responseBody);
        assertEquals("application/json",
                mvcResult.getResponse().getContentType());
    }

    @Transactional
    @Test
    void newType() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                        .post("/gamePlatformType/newType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"gamePlatformName\":\"GBA\"\n" +
                                "}");
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.gamePlatformName",equalTo("GBA")));
    }


    @Test
    void getOneTypeByName() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/gamePlatformType/getOneTypeByName/PS4");

       mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(jsonPath("$.gamePlatformName",equalTo("PS4")))
                .andReturn();
    }

    @Test
    void getOneTypeByKeyName() {
    }

    @Test
    void updateOneType() {
    }

    @Test
    void deleteType() {
    }
}