package kakao.lango.network.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ReductionOperator {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("김영한", "백기선", "정범균", "마틴 파울러", "안태호", "이동욱", "이주현", "김영한");
        System.out.println(list);

        // 스트림 생성하기
        Stream<String> stream = list.stream();
        // 전체 데이터 출력하기
//        stream.forEach(temp -> System.out.print(temp + "\t"));

        // 2개 빼고 출력하기
//        stream.skip(2).forEach(temp -> System.out.print(temp + "\t"));

        // 2개 건너뛰고 3개만 출력하기
//        stream.skip(2).limit(3).forEach(temp -> System.out.print(temp + "\t"));

        // distinct로 중복 제거하기
//        stream.distinct().forEach(temp -> System.out.print(temp + "\t"));

        // 필터링할 filter 사용하여 원하는 조건의 데이터만 골라내기
        // 매개변수가 1개이고 리턴타입이 Boolean인 함수를 대입한다.
//        stream.filter(name -> name.charAt(0) == '김').forEach(temp -> System.out.print(temp + "\t"));
        
        // ㅇ으로 시작하는 데이터만 찾아오기
//        stream.filter(name -> name.charAt(0) >= '아' && name.charAt(0) < '자')
//                .forEach(temp -> System.out.print(temp + "\t"));
        stream.filter(name -> name.charAt(0) >= '아' && name.charAt(0) < '자').sorted()
                .forEach(temp -> System.out.print(temp + "\t"));
        
    }
}
