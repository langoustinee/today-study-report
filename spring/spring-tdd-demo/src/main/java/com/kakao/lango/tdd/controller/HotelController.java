package com.kakao.lango.tdd.controller;

import com.kakao.lango.tdd.dto.HotelRequest;
import com.kakao.lango.tdd.dto.HotelResponse;
import com.kakao.lango.tdd.service.DisplayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
public class HotelController {
    private final DisplayService displayService;
    @ResponseBody
    @PostMapping( "/hotels/fetch-by-name")
    public ResponseEntity<List<HotelResponse>> getHotelByName(@RequestBody HotelRequest hotelRequest) {
        List<HotelResponse> hotelResponses = displayService.getHotelsByName(hotelRequest);
        return ResponseEntity.ok(hotelResponses);
    }
}
