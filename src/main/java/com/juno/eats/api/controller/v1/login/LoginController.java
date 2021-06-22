package com.juno.eats.api.controller.v1.login;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.juno.eats.api.domain.common.CommonApiV1;
import com.juno.eats.api.domain.member.Member;
import com.juno.eats.api.exception.login.JoinValidationException;
import com.juno.eats.api.service.member.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Stack;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
@Slf4j
public class LoginController {

    private final MemberService memberService;

    @PostMapping(value = "/login")
    public ResponseEntity<CommonApiV1> login(@RequestBody String str) {
        String token = memberService.findByMemberId(str);
        LoginResult loginResult = new LoginResult(token);
        CommonApiV1<LoginResult> api = new CommonApiV1<>(true, "0000", "정상적인 회원입니다.", loginResult);
        return new ResponseEntity(api, HttpStatus.OK);
    }

    @PostMapping(value = "/join")
    public ResponseEntity<CommonApiV1> join(@RequestBody String str){
        String[] keys = {"memberId","pw","email"};
        Stack<String> keyCheckStack = keyCheck(str, keys);
        if(!keyCheckStack.isEmpty()){
            throw new JoinValidationException(keyCheckStack.pop());
        }

        Member save = memberService.save(str);
        CommonApiV1<JoinMemberResult> api = new CommonApiV1<>(true, "0000", "정상적으로 가입되었습니다.", new JoinMemberResult(save.getId(), save.getMemberId(), save.getEmail()));
        return new ResponseEntity(api, HttpStatus.OK);
    }

    public Stack<String> keyCheck(String str, String[] keys){
        JsonObject json = new Gson().fromJson(str, JsonObject.class);
        Stack<String> stack = new Stack<>();
        stack.addAll(Arrays.asList(keys));

        int stackSize = stack.size();
        for(int i=0; i<stackSize; i++){
            if(json.keySet().contains(stack.peek())){
                stack.pop();
            }
        }

        return stack;
    }

    @Getter
    class LoginResult{
        private String token;

        public LoginResult(String token) {
            this.token = token;
        }
    }

    @Getter
    class JoinMemberResult{
        private Long id;
        private String memberId;
        private String email;

        public JoinMemberResult(Long id, String memberId, String email) {
            this.id = id;
            this.memberId = memberId;
            this.email = email;
        }
    }
}
