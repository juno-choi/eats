package com.juno.eats.api.domain.login;

import lombok.Getter;

@Getter
public class JoinMemberResult {
    private Long id;
    private String memberId;
    private String email;

    public JoinMemberResult(Long id, String memberId, String email) {
        this.id = id;
        this.memberId = memberId;
        this.email = email;
    }
}
