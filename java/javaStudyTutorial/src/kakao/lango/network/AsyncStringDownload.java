package kakao.lango.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncStringDownload {
    public static void main(String[] args) {
        // 스레드 만들어서 다운로드하기
        // 비동기 방식으로 진행된다.
        new Thread() {
            @Override
            public void run() {
                try {
                    // URL 생성하기
                    URL url = new URL("https://www.kakao.com");
                    // 연결 객체 생성하기
                    // 강제 형 변환으로 타입을 맞출 수 있다.
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    // 연결 옵션 설정하기
                    conn.setConnectTimeout(10000);
                    conn.setUseCaches(false);
                    conn.setRequestMethod("GET");

                    // 읽기위한 스트림 생성하기
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String line = br.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line+"\n");
                    }
                    String html = sb.toString();
                    System.out.println(html);

                    // 자원 반납
                    br.close();
                    conn.disconnect();
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
        }.start();
    }
}
