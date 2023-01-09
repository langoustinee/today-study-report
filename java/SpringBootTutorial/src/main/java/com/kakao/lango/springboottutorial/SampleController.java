package com.kakao.lango.springboottutorial;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// View 대신에 문자열(CSV)이나 JSON을 리턴하는 컨트롤러를 만들고자 할 때 사용하는 어노테이션이다.
@RestController
public class SampleController {
    // sample이라는 요청을 GET 방식으로 요청한 경우 호출될 메서드
    @GetMapping("/sample")
    // String으로 리턴하면 일반 문자열로 출력한다.
    // VO나 List를 리턴하면 JSON 문자열로 출력한다.
    public String[] sample() {
        return new String[] {"STS", "Intelli J"};
    }

}
