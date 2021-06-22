package com.juno.eats.api.service.member;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberServiceTest {

    @Autowired
    private MockMvc mock;

    @Test
    public void 회원원가입_테스트() throws Exception{
        JsonObject json = new JsonObject();
        json.addProperty("memberId", "test");
        json.addProperty("pw", "1234");
        json.addProperty("email", "email");

        //System.out.println(json.toString());
        mock.perform(post("/v1/join").contentType(MediaType.APPLICATION_JSON).content(json.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.code").value("0000"));
    }

    @Test
    public void 회원가입_실패_유효성검사(){

    }
}