package com.kakao.lango.tdd.service;

import com.kakao.lango.tdd.dto.HotelRequest;
import com.kakao.lango.tdd.dto.HotelResponse;

import java.util.List;

public interface DisplayService {
    List<HotelResponse> getHotelsByName(HotelRequest hotelRequest);
}
