package kakao.lango.network.kakaoopenapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class KakaoBookSearch {
    public static void main(String[] args) {
        // 카카오 도서 검색 API
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // url 만들기
                    // GET 방식에서 파라미터는 반드시 인코딩 되어야 합니다.
                    String address = "https://dapi.kakao.com/v3/search/book?target=title";
                    address += "&query=";
                    address += URLEncoder.encode("웹서비스", "utf-8");

                    // Connection 생성하기
                    URL url = new URL(address);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    // 옵션 설정하기
                    conn.setUseCaches(false);
                    conn.setConnectTimeout(30000);
                    conn.setRequestMethod("GET");

                    // 키 설정하기 - Kakao api key
                    conn.setRequestProperty("Authorization", "KakaoAK 5b7faec3964f9cc539acebe122583feb");

                    // 데이터 읽어오기
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();

                    // 읽은 데이터가 없다면 종료하기
                    while (true) {
                        String str = br.readLine();
                        if (str == null) {
                            break;
                        }
                        sb.append(str + "\r\n");
                    }

                    // 읽어온 데이터 출력하기
                    String result = sb.toString();
                    System.out.println(result);
                    
                    // 자원 반납하기
                    br.close();
                    conn.disconnect();

                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
        }).start();
    }
}
