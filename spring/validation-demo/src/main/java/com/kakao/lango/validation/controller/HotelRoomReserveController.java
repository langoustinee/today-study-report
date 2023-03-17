package com.kakao.lango.validation.controller;

import com.kakao.lango.validation.dto.HotelRoomReserveRequestDTO;
import com.kakao.lango.validation.validator.HotelRoomReserveValidator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelRoomReserveController {

    // Validatior 인터페이스를 이용해서 유효성 검사를 할 때 사용할 Validator를 등록하는 메서드
    @InitBinder
    void initBinder(WebDataBinder binder) {
        binder.addValidators(new HotelRoomReserveValidator());
    }

    @PostMapping(path = "/hotels/reserve")
    public ResponseEntity<?> reserveHotelRoomByRoomNumber(
            @Valid @RequestBody HotelRoomReserveRequestDTO reserveRequest,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            String errorMessage = new StringBuilder(bindingResult.getFieldError().getCode())
                    .append(" [").append(fieldError.getField()).append("] ")
                    .append(fieldError.getDefaultMessage())
                    .toString();
            System.out.println("error : " + errorMessage);
//            return ResponseEntity.badRequest().build();
            return ResponseEntity.status(HttpStatus.OK).body(errorMessage);
        }
        System.out.println("유효성 검사 통과");
        return ResponseEntity.status(HttpStatus.OK).body("예약 성공");
    }

}
