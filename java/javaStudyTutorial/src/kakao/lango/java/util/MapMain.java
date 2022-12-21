package kakao.lango.java.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapMain {
    public static void main(String[] args) {
        // Map을 활용하기
        // 데이터의 종류가 1가지라면 Object 대신에 그 자료형을 기재해도 무방하다.
        Map<String, Object> hm = new HashMap<>();

        // 데이터 추가하기
        hm.put("hit", 5);

        // 데이터 가져오기
        System.out.println(hm.get("hit"));

        // 중복된 key를 삽입하기
        hm.put("hit", 10);
        System.out.println(hm.get("hit"));

        // 존재하지 않는 key를 조회한다면 Java에서는 null이다.
        System.out.println(hm.get("number"));

        // Value를 Object로 설정했을 때 사용하기
        // 위에서 Value를 삽입할 때는 String을 이용했지만 Map을 만들 때 Value의 타입은 Object로 설정되었기 떄문에 get으로 데이터를 원상 복구하고자 하면 강제 형 변환을 해야 한다.
        int hitCount = (int) hm.get("hit");
        System.out.println(hitCount);

        // 모든 키를 가져오기
        Set<String> keys = hm.keySet();
        System.out.println(keys);
    }
}
