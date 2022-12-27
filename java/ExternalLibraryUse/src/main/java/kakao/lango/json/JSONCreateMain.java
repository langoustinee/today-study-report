package kakao.lango.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Arrays;

public class JSONCreateMain {
    public static void main(String[] args) {
        ToDoVO vo1 = new ToDoVO(1, 100, "ahah~~ kimkilgu gaseKKi", true);
        ToDoVO vo2 = new ToDoVO(2, 200, "어~느썌 부.터 힙합은 안멋쪄.", false);
        ToDoVO vo3 = new ToDoVO(3, 300, "안녕하세요. 어려분 안녕히계세요.", true);

        // 저장할 JSON 파일을 생성한다.
        File file = new File("./todo.json");

        // JSON 기록을 위한 인스턴스 생성하기
        ObjectMapper mapper = new ObjectMapper();
        try {
            // 기록하기
            mapper.writeValue(file, Arrays.asList(vo1, vo2, vo3));
            System.out.println("데이터 기록 성공!!!");
        } catch (Exception e) {
            System.out.println("데이터 기록 실패...");
        }
    }
}
