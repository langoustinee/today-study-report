package kakao.lango.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTMLParsing {
    public static void main(String[] args) {
        // 웹에서 필요한 문자열 가져오기
        // API Server에서 데이터를 받아오는 부분은 전송방식, 파라미터 부분을 제외하면 거의 동일하다.

        String html = null;
        try {
            // URL 생성하기
            URL url = new URL("https://velog.io/");

            // 다운로드 옵션 설정하기 - 전송방식이나 파라미터 설정
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(30000);
            conn.setUseCaches(false);

            // 결과를 다운로드하기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                // 파싱할 때는
                sb.append(line + "\n");
            }
            html = sb.toString();
//            System.out.println(html);

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return;
        }

        // 문자열에서 원하는 데이터를 파싱(추출)하기
        // json, xml, csv, html 등 데이터 형식에 따라 추출하는 방식은 다르다.
        if (html != null) {
            try {
                // HTML 문자열을 메모리에 DOM 형태로 펼쳐내기
                // import org.jsoup.Jsoup의 Document 객체를 사용하기
                Document document = Jsoup.parse(html);

                // 태그를 가지고 찾기
                Elements elements = document.getElementsByTag("a");
                // 태그는 여러개이기에 순회하여 찾아야한다.
                for (int i = 0; i < elements.size(); i++) {
                    // 하나의 태그 찾기
                    Element element = elements.get(i);
                    // 텍스트 확인하기
//                    System.out.println(element.text());
                    // 속성 확인하기
//                    System.out.println(element.attr("href"));
                }

                // 선택자를 이용하기
                Elements elements2 = document.select("#root > div.sc-bBHHxi.gGMCwe > div.sc-ksdxgE.DNZUI > div.sc-cNKqjZ.jrhkHP > main > div > div:nth-child(1) > div.sc-lbhJGD.cBCWUE > a");
                for (int i = 0; i < elements.size(); i++) {
                    Element element = elements.get(i);
                    // 텍스트 확인하기
                    System.out.println(element.text());
                    // 속성 확인하기
//                    System.out.println(element.attr("href"));
                }
                
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
                return;
            }
        }


        // 데이터를 추출(사용)하기
    }
}
