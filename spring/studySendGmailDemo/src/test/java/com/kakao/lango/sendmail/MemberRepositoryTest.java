package com.kakao.lango.sendmail;

import com.kakao.lango.sendmail.entity.Member;
import com.kakao.lango.sendmail.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void save() {
        IntStream.rangeClosed(1, 3).forEach(i -> {
            Member member = Member.builder()
                    .memberName("member"+i)
                    .password(passwordEncoder.encode("kakao123"))
                    .build();
            memberRepository.save(member);
        });
    }
}
