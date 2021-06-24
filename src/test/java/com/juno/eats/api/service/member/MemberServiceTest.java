package com.juno.eats.api.service.member;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.juno.eats.api.domain.member.Member;
import com.juno.eats.api.repository.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberServiceTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void 테스트전_테스트계정_가입() throws Exception {
        JsonObject json = new JsonObject();
        json.addProperty("memberId", "tester");
        json.addProperty("pw", "1234");
        json.addProperty("email", "email@email.com");

        mock.perform(post("/v1/join").contentType(MediaType.APPLICATION_JSON).content(json.toString()));
    }

    @Test
    void 회원원가입_성공() throws Exception{
        //given
        JsonObject json = new JsonObject();
        json.addProperty("memberId", "test");
        json.addProperty("pw", "1234");
        json.addProperty("email", "email@email.com");

        //when
        //then
        mock.perform(post("/v1/join").contentType(MediaType.APPLICATION_JSON).content(json.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.code").value("0000"));
    }

    @Test
    void 회원원가입_실패() throws Exception{
        //given
        JsonObject json = new JsonObject();
        json.addProperty("memberId", "te");
        json.addProperty("pw", "1234");
        json.addProperty("email", "email");

        //when
        //then
        mock.perform(post("/v1/join").contentType(MediaType.APPLICATION_JSON).content(json.toString()))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.error[0].code").value("0111"))
                .andExpect(jsonPath("$.error[0].message").value("memberId 값의 크기를 확인해주세요."));
    }

    @Test
    void 로그인_성공() throws Exception {
        //given
        String memberId = "tester";
        JsonObject json = new JsonObject();
        json.addProperty("memberId", memberId);
        json.addProperty("pw", "1234");
        //when
        //then
        mock.perform(post("/v1/login").contentType(MediaType.APPLICATION_JSON).content(json.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.code").value("0000"));
    }

    @Test
    void 로그인_실패() throws Exception {
        //given
        String memberId = "틀린아이디";
        JsonObject json = new JsonObject();
        json.addProperty("memberId", memberId);
        json.addProperty("pw", "1234");
        //when
        //then
        mock.perform(post("/v1/login").contentType(MediaType.APPLICATION_JSON).content(json.toString()))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.error[0].message").value("인증에 실패했습니다."))
                .andExpect(jsonPath("$.error[0].code").value("0101"));
    }

}