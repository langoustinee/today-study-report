package kakao.lango.network.lamda;

import java.util.ArrayList;
import java.util.List;

public class MethodParameter {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("통기타");
        list.add("일렉기타");
        list.add("베이스기타");

        // forEach문에 람다식 적용하기
        list.forEach((temp) -> {
            System.out.println(temp);
        });

        // 람다로 구현해야 하는 메서드와 동일한 원형의 메서드를 대입한다.
        list.forEach(System.out::println);
    }
}
