package com.kakao.lango.tdd.service;

import com.kakao.lango.tdd.domain.HotelRoomEntity;
import com.kakao.lango.tdd.dto.HotelRoomResponse;
import com.kakao.lango.tdd.persistence.HotelRoomRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class HotelRoomDisplayService {

    private final HotelRoomRepository hotelRoomRepository;
    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public HotelRoomDisplayService(HotelRoomRepository hotelRoomRepository, ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.hotelRoomRepository = hotelRoomRepository;
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }

    // 테스트할 메소드
    public HotelRoomResponse getHotelRoomById(Long id) {
        HotelRoomEntity hotelRoomEntity = hotelRoomRepository.findById(id);
        // 스레드 풀을 이용하여 스레드 생성 및 수행
        threadPoolTaskExecutor.execute(() -> log.warn("entity: {}", hotelRoomEntity.toString()));
        return HotelRoomResponse.from(hotelRoomEntity);
    }
}
