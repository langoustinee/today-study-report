package com.kakao.lango.reactiveweb.service;

import com.kakao.lango.reactiveweb.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class RestTemplateService {
    private final RestTemplate restTemplate;

    // 파라미터가 없는 GET 요청 처리
    // 리턴 타입은 String
    public String getName() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9000")
                .path("/api/v1/crud-api")
                .encode()
                .build()
                .toUri();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        return responseEntity.getBody();

    }

    // PathVariable 파라미터 GET 요청 처리
    // 리턴 타입은 String
    public String getPathVariable() {
        // 만약 PathVariable이 여러개라면 expend를 나열하면 된다.
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9000")
                .path("/api/v1/crud-api/{name}")
                .encode()
                .build()
                .expand("lango")
                .toUri();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        return responseEntity.getBody();
    }

    // 파라미터가 있는 경우
    // 리턴 타입은 String
    public String getNameWithParameter() {
        // 파라미터가 여러개라면 queryParam을 연속해서 호출하거나 다른 모양의 queryParam을 이용한다.
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9000")
                .path("/api/v1/crud-api")
                .queryParam("name", "lango")
                .encode()
                .build()
                .toUri();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        return responseEntity.getBody();
    }

    // 파라미터와 Body가 존재하는 POST 요청 처리
    public ResponseEntity<MemberDto> postWithParamAndBody() {
        // 아래와 같은 queryParam은 Body에 포함되지 않기 떄문에 반드시 파라미터로 읽어야 한다.
        // @RequestParam으로밖에 읽지 못한다.
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9000")
                .path("/api/v1/crud-api")
                .queryParam("name", "lango")
                .queryParam("email", "lango@kakao.com")
                .queryParam("organization", "kakao-cloud-school")
                .encode()
                .build()
                .toUri();

        // Body에 포함시켜서 데이터를 전송하고 싶다면 아래와 같이 Body로 사용할 객체를 만든다.
        MemberDto dto = MemberDto.builder()
                .name("lango")
                .email("lango@kakao.com")
                .organization("kakao-cloud-school")
                .build();

        // POST 방식으로 전송할 때는 매개변수로 Body를 설정하면 된다.
        ResponseEntity<MemberDto> responseEntity = restTemplate.postForEntity(uri, dto, MemberDto.class);
        return responseEntity;
    }

    // Body와 Header를 모두 전송하는 POST 요청 처리
    public ResponseEntity<MemberDto> postWithHeader() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9000")
                .path("/api/v1/crud-api/add-header")
                .encode()
                .build()
                .toUri();

        // Body에 포함시켜서 데이터를 전송하고 싶다면 아래와 같이 Body로 사용할 객체를 만든다.
        MemberDto dto = MemberDto.builder()
                .name("lango")
                .email("lango@kakao.com")
                .organization("kakao-cloud-school")
                .build();

        // 헤더를 포함시켜서 보내기 위한 객체 생성하기
        RequestEntity<MemberDto> requestEntity = RequestEntity
                .post(uri)
                .header("my-header", "lango-rest-api")
                .body(dto);

        // requestEntity에 post, header, bodt가 포함되었기에 exchange 메소드로 요청을 전송해야 한다.
        ResponseEntity<MemberDto> responseEntity = restTemplate.exchange(requestEntity, MemberDto.class);
        return responseEntity;
    }
}
