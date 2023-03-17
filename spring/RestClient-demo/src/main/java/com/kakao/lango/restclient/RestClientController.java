package com.kakao.lango.restclient;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RestClientController {
    @GetMapping("/urlconnection")
    public ResponseEntity<Map<String, Object>> onlyJavaHttp(){
        List list = new ArrayList();
        // List를 결과로 줄 수 있지만 Map으로 주는게 Restful한 응답방식이다.
        Map<String, Object> res = new HashMap<>();
        res.put("result", false);
        try {
            // 다운로드 받을 URL
            String urlAddr = "https://dapi.kakao.com/v3/search/book?target=title&query=";

            //파라미터 인코딩
            String title = "삼국지";
            urlAddr += URLEncoder.encode(title, "utf-8");
            URL url = new URL(urlAddr);

            // 연결 객체 생성하기
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // 옵션 설정
            // 접속 시도 시간 설정, 이 시간을 설정하지 않으면 서버가 응답하지 않으면 무한으로 대기하게 되는 문제가 발생한다.
            conn.setConnectTimeout(30000);
            // 이전에 다운로드 받은 데이터를 사용하지 않고 새로 다운로드 하도록 설정한다.
            // 데이터가 자주 변경되면 false로 두고 자주 변경되지 않는다면 true로 사용하면 된다.
            conn.setUseCaches(false);
            // 요청 헤더 설정
            conn.setRequestProperty("Authorization", "KakaoAK 5b7faec3964f9cc539acebe122583feb");

            StringBuilder sb = new StringBuilder();
            if (conn != null) {
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStreamReader isr = new InputStreamReader(conn.getInputStream());
                    BufferedReader br = new BufferedReader(isr);
                    while (true) {
                        String line = br.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line);
                    }
                    br.close();
                    conn.disconnect();
                }
            }
            String json = sb.toString();
//            System.out.printf("%s\n", json);

            // 첫번째 파싱
            JSONObject jsonObject = new JSONObject(json);

            // documents 키의 값을 배열로 가져오기
            JSONArray documents = jsonObject.getJSONArray("documents");

            // 배열을 순회하기
            for(int i=0; i<documents.length(); i++) {
                JSONObject object = documents.getJSONObject(i);
                Map<String, Object> map = new HashMap<>();
                map.put("title", object.getString("title"));
                map.put("price", object.getLong("price"));
                System.out.println(map);
                list.add(map);
            }
        } catch (Exception e) {
            System.out.printf("에러 발생:%s\n", e.getMessage());
        }
        System.out.println(list);
        res.put("data", list);
        res.put("result", true);
//        System.out.println(res);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
