package com.kakao.lango.validation.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class HotelRoomReserveRequestDTO {
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String name;
}
