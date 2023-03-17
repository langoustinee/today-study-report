package com.kakao.lango.actuator.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RequestMapping("/exception")
@RestController
public class ExceptionController {

    @GetMapping
    public ResponseEntity<String> getRuntimeException(@RequestParam("su") int su) {
        // 강제로 예외 발생시키기
        if(su == 1) throw new RuntimeException("예외 발생");
        return new ResponseEntity<>("요청 처리 성공", HttpStatus.OK);
    }

    // Controller 단에서 RuntimeException이 발새하면 호출될 예외처리 메소드
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException e, HttpServletRequest request) {
        return new ResponseEntity<>("요청 처리 실패", HttpStatus.BAD_REQUEST);
    }
}
