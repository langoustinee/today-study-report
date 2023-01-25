package com.kakao.lango.springsecurityclub.persistence;

import com.kakao.lango.springsecurityclub.model.ClubMember;
import com.kakao.lango.springsecurityclub.model.ClubMemberRole;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ClubMemberRepositoryTest {

    /**
     * 우리가 주입을 받을 때, @Autowired로 하는 방식과 @RequiredArgsConstructor + final 방식이 있다.
     * 테스트할 때는 @Autowired를 하고 있는데, 그 이유가 @RequiredArgsConstructor를 사용하게 되면
     * final 명령예약어가 사용되고 있는 변수를 생성자 주입으로 사용하게 된다.
     * 근데 테스트 코드를 작성하는 공간은 우리가 흔히 사용하는 스프링 컨테이너 공간이 아닌 JUnit5 이기에
     * 의존성 주입의 타입이 정해져 있어 @Autowired 만 가능한 것이다.
     * JUnit5 는 스스로 DI를 지원한다. DI를 지원하는 타입은 정해져 있다. JUnit5에서 생성자나 lombok 방식으로 DI가 안되는 이유는
     * JUnit이 생성자에 의존성을 주입하려고 먼저 개입하기 때문이다.
     */
    // @RequiredConstructor 어노테이션을 붙이고
    // private final ClubMemberReposutory clubMemberRepository; 를 하게되면 아래와 같은 생성자로 해당 빈을 주입하게 된다.
    /*
    public ClubMemberRepositoryTest(ClubMemberRepository clubMemberRepository) {
        this.clubMemberRepository = clubMemberRepository;
    }
    */

    @Autowired
    private ClubMemberRepository clubMemberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 100명의 회원을 삽입하는 테스트
    @Test
    public void insertMember() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            ClubMember member = ClubMember.builder()
                    .mid("member" + i)
                    .mpw(passwordEncoder.encode("kakao"))
                    .email("user" + "_" + i + "@kakao.com")
                    .name("user@" + i)
                    .social(false)
                    .roleSet(new HashSet<ClubMemberRole>())
                    .build();
            member.addRole(ClubMemberRole.USER);
            if (i > 90) {
                member.addRole(ClubMemberRole.ADMIN);
            }
            clubMemberRepository.save(member);
        });
    }

    // mid를 파라미터로 조회하는 메서드
    @Test
    public void getWithRoles() {
        Optional<ClubMember> result = clubMemberRepository.getWitRoles("member33");
        if (result.isPresent()) {
            System.out.println(result);
            System.out.println(result.get().getRoleSet());
        }
        else {
            System.out.println("존재하지 않는 아이디입니다.");
        }
    }

    @Test
    public void findByEmail() {
        Optional<ClubMember> clubMembers = clubMemberRepository.findByEmail("user_1@kakao.com");
        System.out.println(clubMembers);
        System.out.println(clubMembers.get().getRoleSet());
    }
}
