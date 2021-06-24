package com.juno.eats.api.service.member;

import com.google.gson.*;
import com.juno.eats.api.common.Common;
import com.juno.eats.api.domain.member.Member;
import com.juno.eats.api.exception.login.JoinFailException;
import com.juno.eats.api.exception.login.KeyValidationException;
import com.juno.eats.api.exception.login.LoginFailException;
import com.juno.eats.api.repository.member.MemberRepository;
import com.juno.eats.api.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Stack;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member save(String str){

        String[] keys = {"memberId","pw","email"};
        Stack<String> keyCheckStack = Common.keyCheck(str, keys);
        if(!keyCheckStack.isEmpty()){
            throw new KeyValidationException(keyCheckStack.pop());
        }

        JsonObject json = new Gson().fromJson(str, JsonObject.class);

        String memberId = json.get("memberId").getAsString();
        String email = json.get("email").getAsString();
        String pw = json.get("pw").getAsString();

        boolean validation = true;

        if(memberId.length()<4){
            throw new JoinFailException("memberId");
        }else if(email.length()<3){
            throw new JoinFailException("email");
        }else if(pw.length()<4){
            throw new JoinFailException("pw");
        }

        Member member = Member
                .builder()
                .memberId(memberId)
                .email(email)
                .pw(passwordEncoder.encode(pw))
                .build();

        return memberRepository.save(member);
    }

    public Member findOne(Long id) throws Exception{
        Member member = memberRepository.findOne(id);
        if(member==null){
            throw new Exception();
        }
        return member;
    }

    public String findByMemberId(String str) {

        String[] keys = {"memberId", "pw"};
        Stack<String> keyCheckStack = Common.keyCheck(str, keys);
        if(!keyCheckStack.isEmpty()){
            throw new KeyValidationException(keyCheckStack.pop());
        }

        JsonObject json = new Gson().fromJson(str, JsonObject.class);
        Member member = memberRepository.findByMemberId(json.get("memberId").getAsString());
        // 로그인하려는 아이디가 존재하지 않을때
        if(member == null){
            throw new LoginFailException();
        }
        //비밀번호가 일치하지 않을 때
        if(!passwordEncoder.matches(json.get("pw").getAsString(),member.getPassword())){
            throw new LoginFailException();
        }

        String token = jwtTokenProvider.createToken(String.valueOf(member.getId()), member.getRoles());

        return token;
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }
}
