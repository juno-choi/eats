package com.juno.eats.api.controller.v1.login;

import com.juno.eats.api.domain.common.CommonApiV1;
import com.juno.eats.api.domain.login.JoinMemberResult;
import com.juno.eats.api.domain.login.LoginResult;
import com.juno.eats.api.domain.member.Member;
import com.juno.eats.api.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
@Slf4j
public class LoginController {

    private final MemberService memberService;

    @PostMapping(value = "/login")
    public ResponseEntity<CommonApiV1> login(@RequestBody String str) {
        String token = memberService.findByMemberId(str);
        log.info("로그인 후 정상 토근발급 = {}",token);
        LoginResult loginResult = new LoginResult(token);
        CommonApiV1<LoginResult> api = new CommonApiV1<>(true, "0000", "정상적인 회원입니다.", loginResult);
        return new ResponseEntity(api, HttpStatus.OK);
    }

    @PostMapping(value = "/join")
    public ResponseEntity<CommonApiV1> join(@RequestBody String str){
        Member save = memberService.save(str);
        log.info("신규회원 가입 memberId = {}", save.getMemberId());
        CommonApiV1<JoinMemberResult> api = new CommonApiV1<>(true, "0000", "정상적으로 가입되었습니다.", new JoinMemberResult(save.getId(), save.getMemberId(), save.getEmail()));
        return new ResponseEntity(api, HttpStatus.OK);
    }


}
