package com.kakao.lango.tdd.persistence;

import com.kakao.lango.tdd.domain.HotelRoomEntity;
import org.springframework.stereotype.Repository;

@Repository
public class HotelRoomRepository {
    public HotelRoomEntity findById(Long id) {
        return new HotelRoomEntity(id, "EAST-1902", 19, 2, 1);
    }
}
