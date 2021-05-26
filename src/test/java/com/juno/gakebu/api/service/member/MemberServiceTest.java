package com.juno.gakebu.api.service.member;

import com.juno.gakebu.api.domain.member.Member;
import com.juno.gakebu.api.repository.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입(){
        Member member = Member.builder().memberId("user3").name("최준영").build();

        Member saveMember = memberService.save(member);

        Assertions.assertThat(saveMember.getName()).isEqualTo("최준영");
    }

}