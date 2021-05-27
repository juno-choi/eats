package com.juno.gakebu.api.service.member;

import com.juno.gakebu.api.domain.member.Member;
import com.juno.gakebu.api.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member save(Member member){
        return memberRepository.save(member);
    }

    public Member findOne(Long id) throws Exception{
        Member member = memberRepository.findOne(id);
        if(member==null){
            throw new Exception();
        }
        return member;
    }
    public Member findOneByMemberId(String memberId) throws Exception{
        Member member = memberRepository.findOneByMemberId(memberId);
        if(member==null){
            throw new Exception();
        }
        return member;
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }
}
