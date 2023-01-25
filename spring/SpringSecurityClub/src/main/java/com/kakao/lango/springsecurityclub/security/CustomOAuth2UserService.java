package com.kakao.lango.springsecurityclub.security;

import com.kakao.lango.springsecurityclub.model.ClubMember;
import com.kakao.lango.springsecurityclub.model.ClubMemberRole;
import com.kakao.lango.springsecurityclub.persistence.ClubMemberRepository;
import com.kakao.lango.springsecurityclub.security.dto.ClubMemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final ClubMemberRepository clubMemberRepository;
    private final PasswordEncoder passwordEncoder;

    // kakao login 성공 후 넘어노는 데이터를 이용하여 email을 추출하고 리턴하는 사용자 정의 메소드
    private String getKakaoEmail(Map<String, Object> map) {
        // 카카오 계정 정보가 있는 Map을 추출한다.
        Object value = map.get("kakao_account");
        log.info("Kakao account: " + value);
        LinkedHashMap accountMap = (LinkedHashMap) value;
        String email = (String) accountMap.get("email");
        log.info("kakao email: " + email);
        return email;
    }

    // 실제 로그인이 성공했을 때 호출되는 메소드를 재정의하기
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("userRequest: " + userRequest);
        // 로그인에 성공한 서비스의 정보 가져오기
        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String clientName = clientRegistration.getClientName();
        log.info("Service Name: " + clientName);

        // 계정에 대한 정보를 가져오기
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> map = oAuth2User.getAttributes();
        String email = null;
        log.warn(clientName);
        switch (clientName.toLowerCase()) {
            case "kakao":
                email = getKakaoEmail(map);
                break;
        }
        log.info("email: " + email);
        return generateDTO(email, map);
    }

    // 이메일 정보가 있으면 해당하는 DTO를 반환하고 없다면 회원가입 후 DTO를 리턴하는 사용자 정의 메소드
    private ClubMemberSecurityDTO generateDTO(String email, Map<String, Object> params) {
        // email을 통해 데이터 찾아오기
        Optional<ClubMember> result = clubMemberRepository.findByEmail(email);

        //데이터베이스에 해당 이메일을 가진 사용자가 없다면 회원을 추가하기
        if (result.isEmpty()) {
            //회원 추가하기(mid는 이메일 주소, 패스워드는 kakao)
            ClubMember member = ClubMember.builder()
                    .mid(email)
                    .mpw(passwordEncoder.encode("kakao"))
                    .email(email)
                    .social(true)
                    .build();
            member.addRole(ClubMemberRole.USER);
            clubMemberRepository.save(member);
            
            // 회원가입한 사용자의 MemberSecurityDTO 반환하기
            ClubMemberSecurityDTO memberSecurityDTO = new ClubMemberSecurityDTO(
                    email,
                    "kakao",
                    email,
                    null,
                    true,
                    false,
                    Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
            memberSecurityDTO.setProps(params);
            return memberSecurityDTO;
        }
        // 해당 이메일을 가진 사용자가 있다면 DTO 바로 반환하기
        else {
            ClubMember member = result.get();
            ClubMemberSecurityDTO memberSecurityDTO = new ClubMemberSecurityDTO(
                    member.getMid(),
                    member.getMpw(),
                    member.getEmail(),
                    member.getName(),
                    member.isDel(),
                    member.isSocial(),
                    member.getRoleSet()
                            .stream().map(memberRole -> new SimpleGrantedAuthority("ROLE_"+memberRole.name()))
                            .collect(Collectors.toList())
            );
            return memberSecurityDTO;
        }
    }
}