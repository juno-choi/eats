package com.juno.gakebu.api.service.member;

import com.google.gson.*;
import com.juno.gakebu.api.domain.member.Member;
import com.juno.gakebu.api.repository.member.MemberRepository;
import com.juno.gakebu.api.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member save(String str){

        JsonObject json = new Gson().fromJson(str, JsonObject.class);

        Member member = Member
                .builder()
                .memberId(json.get("memberId").getAsString())
                .email(json.get("email").getAsString())
                .pw(json.get("pw").getAsString())
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
        JsonObject json = new Gson().fromJson(str, JsonObject.class);
        Member member = memberRepository.findByMemberId(json.get("memberId").getAsString());
        String token = jwtTokenProvider.createToken(String.valueOf(member.getId()), member.getRoles());

        return token;
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }
}
