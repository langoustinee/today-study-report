package kakao.lango.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ToDoJSONParsingMain {
    public static void main(String[] args) {
        // 데이터 다운로드
        // 다운로드 받은 문자열을 저장하기
        String json = null;

        try {
            // 다운로드 받기 위한 URL 생성하기
            // URL에 한글이 포함되었다면 URLEncoder.encode() 메서드를 이용해 인코딩해야한다.
            URL url = new URL("https://jsonplaceholder.typicode.com/todos");
            // URL에 연결하기
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 옵션 설정하기
            // Kakao와 같은 Open API 들은 key를 요구할 수 있는데, conn.setRequestProperty(키이름, 키) 옵션을 추가로 설정해주면 된다.
            conn.setRequestMethod("GET"); // 요청방식
            conn.setConnectTimeout(30000); // 접속 요청 제한 시간
            conn.setUseCaches(false); // 캐싱된 데이터 사용 여부

            // 문자열을 읽기 위한 스트림 생성하기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            // 많은 양의 문자열 읽어오기
            StringBuilder sb = new StringBuilder();

            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line + "\n");
            }
            json = sb.toString();
//            System.out.println(json);

        } catch (Exception e) {
            System.out.println("데이터 다운로드 실패...");
//            System.exit(0); // 프로그램 종료
            return; // main 종료
        }

        // 다운로드 받은 데이터 파싱
        List<ToDoVO> list = new ArrayList<>();
        try {
            if(json != null) {
                // 전체 문자열을 배열로 변환한다.
                JSONArray arr = new JSONArray(json);
//                System.out.println(arr);

                // 배열을 순회하기
                int len = arr.length();
                for (int i = 0; i < len; i++) {
                    // 배열의 요소를 json 객체로 가져오기
                    JSONObject obj = arr.getJSONObject(i);
//                    System.out.println(obj);
                    ToDoVO vo = new ToDoVO();
                    vo.setUserid(obj.getInt("userId"));
                    vo.setId(obj.getInt("id"));
                    vo.setTitle(obj.getString("title"));
                    vo.setCompleted(obj.getBoolean("completed"));
                    list.add(vo);
                }
            }
        } catch (Exception e) {
            System.out.println("데이터 파싱 실패...");
            return;
        }
        
        // 파싱한 결과 사용(출력)하기
        for (ToDoVO vo : list) {
            System.out.println(vo);
        }
    }

}
