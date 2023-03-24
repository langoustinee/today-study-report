package com.kakao.lango.springbatch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.support.CronExpression;

import java.time.LocalDateTime;

@SpringBootTest
public class CronTest {
    @Test
    public void testParse() {
        // 5초마다 수행하기
        CronExpression expres = CronExpression.parse("0/5 * * * * ?");
        // 기준 시간 설정
        LocalDateTime next = expres.next(LocalDateTime.of(2023, 1, 1, 0, 0, 0));

        // 테스트에서는 확인하기 위한 출력문을 작성하면 안된다.
        // 출력하는 것은 테스트가 아니라 디버깅일 뿐이다.
        System.out.println(next);

        // 실제 테스트 로직
        Assertions.assertEquals("2023-01-01T00:00:05", next.toString());
        Assertions.assertEquals("2023-01-01T00:00:10", expres.next(next).toString());

    }
}
