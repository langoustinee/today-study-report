package com.example.communications.controller;

import com.example.communications.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/crud-api")
@RestController
public class CrudController {
    @GetMapping()
    public String getName() {
        return "lango";
    }

    @GetMapping(value = "/{variable}")
    public String getVariable(@PathVariable String variable) {
        return variable;
    }

    // @RequestParam이 없어도 파라미터를 변수로 받을 수 있다. 다만, @RequestParam이 없다면 변수가 파라미터가 여러 개일 때 어떤 변수에 대입될지 알 수 없다.
    // @RequestParam(파라미터이름) 변수를 작성하면 파라미터 이름과 일치하는 데이터를 찾아서 변수에 대입한다.
    // @RequestParam(name = "name") String name, @RequestParam(name = "email") String email 이런식으로 해야한다.
    // DTO(Command Instance)로 여러 개의 파라미터를 한꺼번에 받을 수 있지만 가독성이 떨어진다.
    // 그래서 최근에는 DTO와 @RequestParam을 함꼐 작성한다.
    // getParameter(@RequestParam String name, MemberDto dto) 형식으로 모두 작성한다.
    @GetMapping("/param")
    public String getParameter(@RequestParam String name) {
        return "Hello " + name;
    }

    // JSON 응답을 하려면 DTO나 Map 또는 Collentins를 반환하면 되지만
    // ResponseEntity를 사용하면 상태를 같이 전송할 수 있기 떄문에 많이 사용한다.
    // 그리고 혼자 개발하는 경우가 아니라면 Collections를 반환하는 것을 지양해야 한다.
    // 최근에는 Map보다 DTO를 권장하도록 하고 있다.
    // @RequestBody는 클라이언트가 데이터를 body에 포함시켜 전송할 때 받을 수 있는 방법이다.
    @PostMapping
    public ResponseEntity<MemberDto> getMember(
            @RequestBody MemberDto request,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("organization") String organization
    ) {
        System.out.println(request);
        MemberDto dto = MemberDto.builder()
                .name(name)
                .email(email)
                .organization(organization)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping("/add-header")
    public ResponseEntity<MemberDto> addHeader(
            @RequestHeader("my-header") String header,
            @RequestBody MemberDto dto
    ) {
        System.out.println(header);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
}
