package com.kakao.lango.tdd.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class HotelRequest {
    private String hotelName;
    public HotelRequest() {
    }

    public HotelRequest(String hotelName) {
        this.hotelName = hotelName;
    }
}