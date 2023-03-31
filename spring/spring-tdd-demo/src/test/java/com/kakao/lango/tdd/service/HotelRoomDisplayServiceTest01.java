package com.kakao.lango.tdd.service;

import com.kakao.lango.tdd.config.TestConfig;
import com.kakao.lango.tdd.dto.HotelRoomResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class) // 설정 클래스를 지정한다.
@TestPropertySource(locations = "classpath:application-test.properties")
//@TestPropertySource(properties = {"spring.config.location = classpath:/application-test.yml"})
public class HotelRoomDisplayServiceTest01 {
    @Autowired
    private HotelRoomDisplayService hotelRoomDisplayService;
    @Test
    public void testTestConfiguration() {
        HotelRoomResponse hotelRoomResponse = hotelRoomDisplayService.getHotelRoomById(1L);
        // Not Null 검증하기
        Assertions.assertNotNull(hotelRoomResponse);
        // 유효성 검증하기
        Assertions.assertEquals(1L, hotelRoomResponse.getHotelRoomId());
    }
}
