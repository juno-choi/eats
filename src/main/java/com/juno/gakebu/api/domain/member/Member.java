package com.juno.gakebu.api.domain.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true, length = 20)
    private String memberId;
    @Column(nullable = false, unique = true, length = 30)
    private String name;

}
