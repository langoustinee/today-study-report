package com.kakao.lango.actuator.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

// RestController에서 예외가 발생했을 때 데이터를 넘겨주기 위한 예외 클래스
@Log4j2
@RestControllerAdvice
public class CustomExceptionHandler {
    //처리할 예외 클래스를 지정해서 Controller에서 예외가 발생했을 때 호출
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleException(RuntimeException e, HttpServletRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        // 어떤 요청이 왔을 때 어떤 예외가 발생했는지 로깅한다.
        log.error("Advice 내 exceptionHandler 호출, {}, {}", request.getRequestURI(), e.getMessage());
        
        // 전송할 데이터 생성하기
        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", e.getMessage());
        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }
}


