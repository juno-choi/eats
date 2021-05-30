package com.juno.gakebu.api.controller.v1.login;

import com.juno.gakebu.api.domain.common.CommonApi;
import com.juno.gakebu.api.domain.common.SingleApi;
import com.juno.gakebu.api.domain.member.Member;
import com.juno.gakebu.api.security.JwtTokenProvider;
import com.juno.gakebu.api.service.common.Response;
import com.juno.gakebu.api.service.member.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Api(tags = {"1. login"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class LoginController {

    private final MemberService memberService;
    private final Response response;

    @ApiOperation(value = "로그인", notes = "회원 로그인")
    @PostMapping(value = "/login")
    public SingleApi<LoginResult> login(@RequestBody String str) {
        String token = memberService.findByMemberId(str);
        LoginResult loginResult = new LoginResult();
        loginResult.setToken(token);
        return response.getSingleApi(loginResult);
    }

    @ApiOperation(value = "가입", notes = "회원 가입")
    @PostMapping(value = "/join")
    public CommonApi join(@RequestBody String str){
        memberService.save(str);
        return response.getSuccessResult();
    }

    @Data
    class LoginModel{
        String memberId;
        String email;
        String pw;
    }

    @Data
    class LoginResult{
        String token;
    }
}
