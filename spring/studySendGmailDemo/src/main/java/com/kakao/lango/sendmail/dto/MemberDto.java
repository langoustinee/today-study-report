package com.kakao.lango.sendmail.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@ToString
public class MemberDto extends User {
    private String memberId;
    private String password;

    public MemberDto(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.memberId = username;
        this.password = password;
    }
}
