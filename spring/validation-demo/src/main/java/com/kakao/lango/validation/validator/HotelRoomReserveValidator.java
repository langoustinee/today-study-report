package com.kakao.lango.validation.validator;


import com.kakao.lango.validation.dto.HotelRoomReserveRequestDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

public class HotelRoomReserveValidator implements Validator {
    // 유효성 검사를 수행할 클래스 가능 여부를 리턴하는 메소드
    @Override
    public boolean supports(Class<?> clazz) {
        return HotelRoomReserveRequestDTO.class.equals(clazz);
    }

    // 유효성 검사를 수행하는 메소드
    @Override
    public void validate(Object target, Errors errors) {
        HotelRoomReserveRequestDTO request = HotelRoomReserveRequestDTO.class.cast(target);
        // checkInDate가 Null 인 경우
        if (Objects.isNull(request.getCheckInDate())) {
            errors.rejectValue("checkInDate", "NotNull", "checkInDate is null");
            return;
        }
        // checkOutDate가 Null 인 경우
        if (Objects.isNull(request.getCheckOutDate())) {
            errors.rejectValue("checkOutDate", "NotNull", "checkOutDate is null");
            return;
        }
        if (request.getCheckInDate().compareTo(request.getCheckOutDate()) >= 0) {
            errors.rejectValue("checkOutDate", "Constraint Error", "checkOutDate is earlier than checkInDate ");
            return;
        }
    }
}
