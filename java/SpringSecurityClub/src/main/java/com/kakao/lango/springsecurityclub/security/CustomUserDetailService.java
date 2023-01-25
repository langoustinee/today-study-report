package com.kakao.lango.springsecurityclub.security;

import com.kakao.lango.springsecurityclub.model.ClubMember;
import com.kakao.lango.springsecurityclub.persistence.ClubMemberRepository;
import com.kakao.lango.springsecurityclub.security.dto.ClubMemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
    private final ClubMemberRepository clubMemberRepository;

    // 아이디를 입력하고 로그인 요청을 하게되면 아이디에 해당하는 데이터를 찾아오는 메소드
    // 로그인 처리를 해야 한다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("[CustomUserDetailService] loadUserByUsername: " + username);

        // 로그인에 성공했을 경우

        // 아래 설정은 하나의 가상 유저를 대상으로 테스트하기 위해 추가한 설정이다.
        // 실제로는 데이터베이스에서 읽어서 설정해야 한다.
        /*
        UserDetails userDetails = User.builder()
                .username("kakao")
                .password(passwordEncoder.encode("kakao"))
                .authorities("ROLE_USER")
                .build();
        return userDetails;
        */

        Optional<ClubMember> result = clubMemberRepository.getWitRoles(username);
        if (result.isEmpty()) {
            throw new UsernameNotFoundException("존재하지 않는 이름입니다.");
        }

        ClubMember member = result.get();
        ClubMemberSecurityDTO clubMemberSecurityDTO = new ClubMemberSecurityDTO(
                member.getMid(),
                member.getMpw(),
                member.getEmail(),
                member.getName(),
                false,
                member.isDel(),
                member.getRoleSet()
                        .stream().map(memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole.name()))
                        .collect(Collectors.toList())
        );
        return clubMemberSecurityDTO;
    }
}
