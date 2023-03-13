package com.kakao.lango.sendmail.security;

import com.kakao.lango.sendmail.dto.MemberDto;
import com.kakao.lango.sendmail.entity.Member;
import com.kakao.lango.sendmail.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> result = memberRepository.findByMemberName(username);
        Member member = result.orElseThrow(() -> new
                UsernameNotFoundException("Cannot find mid"));
        log.info("APIUserDetailsService apiUser-------------------------------------");
        MemberDto dto = new MemberDto(
                member.getMemberId(),
                member.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
        log.info(dto);
        return dto;
    }
}
