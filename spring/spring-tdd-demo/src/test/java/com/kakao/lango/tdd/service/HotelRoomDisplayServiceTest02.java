package com.kakao.lango.tdd.service;

import com.kakao.lango.tdd.domain.HotelRoomEntity;
import com.kakao.lango.tdd.dto.HotelRoomResponse;
import com.kakao.lango.tdd.persistence.HotelRoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

// static 메소드 import 하기
// 클래스의 특정 static 멤버만 import 할 수 있다.
// 일반적으로 가독성을 높이기 위해서 사용한다.
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class HotelRoomDisplayServiceTest02 {
    @Autowired
    private HotelRoomDisplayService hotelRoomDisplayService;

    // 가짜 객체 생성
    @MockBean
    private HotelRoomRepository hotelRoomRepository;

    @Test
    public void testMockBean() {
        // 구현이 안된 메서드를 호출하여 결과를 만들어 낸다.
        // any()는 아무것이나 대입하면 willReturn구문의 값을 주도록 설정한다.
        // 이렇게 가짜로 메서드를 수행하는 경우, 이 메서드를 stub라고 부른다.
        given(this.hotelRoomRepository.findById(any())).willReturn(new HotelRoomEntity(10L, "test", 1, 1, 1));
        HotelRoomResponse hotelRoomResponse = hotelRoomDisplayService.getHotelRoomById(1L);
        Assertions.assertNotNull(hotelRoomResponse);
        Assertions.assertEquals(10L, hotelRoomResponse.getHotelRoomId());
    }
}
