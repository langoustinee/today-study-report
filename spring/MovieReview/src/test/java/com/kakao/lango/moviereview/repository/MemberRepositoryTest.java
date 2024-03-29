package com.kakao.lango.moviereview.repository;

import com.kakao.lango.moviereview.domain.Member;
import com.kakao.lango.moviereview.persistence.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertMember() {
        IntStream.rangeClosed(1, 50).forEach(i -> {
            Member member = Member.builder()
                    .email("kakao-cloud-" + i + "@kaka.com")
                    .password("kakao" + i)
                    .nickname("Reviewer@" + i)
                    .build();
            memberRepository.save(member);

        });
    }

    @Test
    public void findMember() {
        String nickname = "Reviewer@1";
        Member member = memberRepository.findMemberByNickname(nickname);
        System.out.println(member);
    }

}
