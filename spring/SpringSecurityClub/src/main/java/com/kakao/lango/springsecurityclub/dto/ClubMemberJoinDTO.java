package com.kakao.lango.springsecurityclub.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@ToString
public class ClubMemberJoinDTO {
    private String mid;
    private String mpw;
    private String email;
    private String name;
    private boolean social;
    private boolean del;
}
