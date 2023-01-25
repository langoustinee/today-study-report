package com.kakao.lango.springbootmybatis;

import com.kakao.lango.springbootmybatis.persistence.MemoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisTest {
    @Autowired
    MemoMapper memoMapper;

    @Test
    public void mybatis() {
        System.out.println(memoMapper);
        System.out.println(memoMapper.allMemo());

    }
}
