package com.juno.gakebu.api.domain.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true, length = 20)
    private String memberId;
    @Column(nullable = false, unique = false, length = 30)
    private String name;
    @Column(nullable = true, length = 100)
    private String pw;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.pw;
    }

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Override
    public String getUsername() {
        return this.memberId;
    }

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
