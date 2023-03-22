package com.kakao.lango.reactiveweb.service;

import com.kakao.lango.reactiveweb.dto.MemberDto;
import org.apache.hc.core5.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class WebClientService {

    // 매개변수 없는 Get 요청 처리
    public String getName() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:9000")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        // 동기식 요청 처리, RestTesmplate과 동일한 방식
        return webClient.get()
                .uri("/api/v1/crud-api")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    // PathVariable이 있는 Get 요청 처리
    public String getNameWithPathVariable() {
        WebClient webClient = WebClient.create("http://localhost:9000");

        ResponseEntity<String> responseEntity = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/v1/crud-api/{name}")
                        .build("lango2"))
                .retrieve()
                .toEntity(String.class)
                .block();

        ResponseEntity<String> responseEntity1 = webClient.get()
                .uri("/api/v1/crud-api/{name}", "lango2")
                .retrieve()
                .toEntity(String.class)
                .block();

        return responseEntity.getBody();
    }

    // 파라미터가 있는 Get 요청 처리
    public String getNameWithParameter() {
        WebClient webClient = WebClient.create("http://localhost:9000");
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/api/v1/crud-api")
                        .queryParam("name", "lango3")
                        .build())
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(String.class);
                    } else {
                        return clientResponse.createException().flatMap(Mono::error);
                    }
                })
                .block();
    }

    public ResponseEntity<MemberDto> postWithParamAndBody() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:9000")
                .defaultHeader(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE)
                .build();
        MemberDto dto = MemberDto.builder()
                .email("lango4@kakao.com")
                .name("lango4")
                .organization("kakao-cloud-school")
                .build();

        return webClient.post().uri(uriBuilder -> uriBuilder.path("/api/v1/crud-api")
                        .queryParam("name", "lango4")
                        .queryParam("email", "lango4@kakao.com")
                        .queryParam("organization", "kakao-cloud-school")
                        .build())
                .bodyValue(dto)
                .retrieve()
                .toEntity(MemberDto.class)
                .block();
    }

    public ResponseEntity<MemberDto> postWithHeader() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:9000")
                .defaultHeader(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE)
                .build();
        MemberDto dto = MemberDto.builder()
                .email("lango4@kakao.com")
                .name("lango4")
                .organization("kakao-cloud-school")
                .build();

        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder.path("/api/v1/crud-api/add-header")
                        .build())
                .bodyValue(dto)
                .header("my-header", "LANGO-API")
                .retrieve()
                .toEntity(MemberDto.class)
                .block();
    }
}
