package com.cj.cga101g1.gameplatformtype.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
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
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/gameplatformtype/getOneType/{gamePlatformNo}",64001);

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.gamePlatformName",notNullValue()))
                .andExpect(jsonPath("$.gamePlatformName",equalTo("Switch")))
                .andReturn();

        assertEquals("application/json",
                mvcResult.getResponse().getContentType());
    }

    @Transactional
    @Test
    void newType() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                        .post("/gameplatformtype/newType")
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
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/gameplatformtype/getOneTypeByName/Switch");

       mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(jsonPath("$.gamePlatformName",equalTo("Switch")))
                .andReturn();
    }

    @Test
    void getOneTypeByKeyName() {
    }

    @Test
    void updateOneType() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/gameplatformtype/updateOneType/{gamePlatformNo}",64001)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"gamePlatformName\":\"GBA\"\n" +
                        "}");
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.gamePlatformName",equalTo("GBA")));
    }

    @Transactional
    @Test
    void deleteType() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/gameplatformtype/deleteType/{gamePlatformNo}",64001);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
    }
}