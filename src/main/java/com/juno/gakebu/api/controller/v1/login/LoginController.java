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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Api(tags = {"1. login"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class LoginController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;
    private final Response response;
    private final PasswordEncoder passwordEncoder;


    @ApiOperation(value = "로그인", notes = "회원 로그인")
    @GetMapping(value = "/signin")
    public SingleApi<String> login(@ApiParam(value = "로그인 회원 모델") LoginModel loginModel) throws Exception {
        Member member = memberService.findOneByMemberId(loginModel.getMemberId());

        return response.getSingleApi(jwtTokenProvider.createToken(String.valueOf(member.getId()), member.getRoles()));
    }

    @ApiOperation(value = "가입", notes = "회원 가입")
    @PostMapping(value = "/join")
    public CommonApi join(LoginModel loginModel){
        memberService.save(
                Member.builder()
                        .memberId(loginModel.getMemberId())
                        .name(loginModel.getName())
                        .pw(passwordEncoder.encode(loginModel.getPw()))
                        .roles(Collections.singletonList("ROLE_USER"))
                        .build());
        return response.getSuccessResult();
    }

    @Data
    class LoginModel{
        String memberId;
        String name;
        String pw;
    }
}
