package com.juno.gakebu.api.repository.member;

import com.juno.gakebu.api.domain.member.Member;
import com.juno.gakebu.api.domain.member.QMember;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;



    public Member save(Member member){
        em.persist(member);
        return member;
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public Member findOneByMemberId(String memberId) {
        return em.createQuery("select m from Member m where m.memberId = :memberId", Member.class).setParameter("memberId", memberId).getSingleResult();
    }

    public Member findByMemberId(String memberId){
        QMember member = QMember.member;
        JPAQueryFactory query = new JPAQueryFactory(em);

        return (Member)query.from(member).where(member.memberId.eq(memberId)).fetchOne();
    }
}

