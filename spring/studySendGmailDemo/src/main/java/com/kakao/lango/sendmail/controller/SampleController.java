package com.kakao.lango.sendmail.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/sample")
public class SampleController {
    @GetMapping("/lango")
    public List<String> lango(){
        return Arrays.asList("Java", "Spring", "JPA", "MariaDB");
    }
}
