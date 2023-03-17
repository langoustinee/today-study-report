package com.kakao.lango.validation.controller;

import com.kakao.lango.validation.dto.ValidRequestDTO;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequestMapping("/validation")
@RestController
public class ValidationController {

    // JSON 형식으로 파라미터를 받는다면 @RequestBody로 사용하면 된다.
    @PostMapping("/valid")
    public ResponseEntity<String> checkValidationByValid(@RequestBody ValidRequestDTO validRequestDTO) {
        log.info(validRequestDTO.toString());
        return ResponseEntity.status(HttpStatus.OK).body(validRequestDTO.toString());
    }
}
