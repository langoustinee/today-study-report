package com.kakao.lango.reactiveweb.controller;

import com.kakao.lango.reactiveweb.dto.MemberDto;
import com.kakao.lango.reactiveweb.service.RestTemplateService;
import com.kakao.lango.reactiveweb.service.WebClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest-template")
@RequiredArgsConstructor
@RestController
public class RestTemplateController {
//    private final RestTemplateService service;

    private final WebClientService service;

    @GetMapping
    public String getName() {
        return service.getName();
    }

    @GetMapping("/path")
    public String getNameWithPathVariable() {
        return service.getNameWithPathVariable();
    }

    @GetMapping("/parameter")
    public String getNameWithParameter() {
        return service.getNameWithParameter();
    }

    @PostMapping
    public ResponseEntity<MemberDto> postWithParamAndBody() {
        return service.postWithParamAndBody();
    }

    @PostMapping("/header")
    public ResponseEntity<MemberDto> postWithHeader() {
        return service.postWithHeader();
    }
}
