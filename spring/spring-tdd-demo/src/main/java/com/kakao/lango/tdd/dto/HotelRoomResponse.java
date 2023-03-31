package com.kakao.lango.tdd.dto;

import com.kakao.lango.tdd.domain.HotelRoomEntity;
import lombok.Getter;

@Getter
public class HotelRoomResponse {
    private Long hotelRoomId;
    private String code;
    private Integer floor;
    private Integer bedCount;
    private Integer bathCount;

    private HotelRoomResponse(Long hotelRoomId, String code, Integer floor,
                              Integer bedCount, Integer bathCount) {
        this.hotelRoomId = hotelRoomId;
        this.code = code;
        this.floor = floor;
        this.bedCount = bedCount;
        this.bathCount = bathCount;
    }

    public static HotelRoomResponse from(HotelRoomEntity entity) {
        return new HotelRoomResponse(entity.getId(),
                entity.getCode(),
                entity.getFloor(),
                entity.getBedCount(),
                entity.getBathCount());
    }
}

