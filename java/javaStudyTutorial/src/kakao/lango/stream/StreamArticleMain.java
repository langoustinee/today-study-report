package kakao.lango.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class StreamArticleMain {
    public static void main(String[] args) {
        // Articles 클래스의 List 생성하기
        List<Articles> list = new ArrayList<>();
        list.add(new Articles(1, "질문", "1번 질문입니다.", "머리를 기를까요 자를까요?", 5));
        list.add(new Articles(2, "일반", "오늘 날씨 춥네요.", "영하로 떨어졌네요", 1));
        list.add(new Articles(3, "질문", "짜장면 VS 짬뽕", "골라주세요... 배고파요", 10));
        list.add(new Articles(4, "질문", "맥북 M1 PRO랑 에어중에 고민이 되네요.", "그냥 비싼게 좋겠죠?", 2));
        list.add(new Articles(5, "일반", "소개팅 다녀왔어요", "까였네요...", 3));

        // scores의 합계를 구하기
        // Articles를 Articles.getScores 메서드의 결과를 이용하여 정수로 변환한다.
        int sumScores = list.stream()// 생성
                .mapToInt(Articles::getScores) // 중간연산
                .sum(); // 최종연산
        System.out.println("합계: " + sumScores);

        // 평균 구하기
        // OptionalDouble 자료형은 null 여부를 확인한 후 사용한다.
        OptionalDouble avg = list.stream()
                .mapToInt(Articles::getScores)
                .average();
        if (avg.isPresent() == true) System.out.println("평균: " + avg.getAsDouble());
        else System.out.println("평균을 구할 수 없습니다.");

        // reduce: 집계
        // 매개변수가 2개이고 리턴이 있는 메서드를 제공한다.
        // 처음 2개의 데이터를 가지고 메서드를 호출하여 결과를 만들고 다음부터는 결과의 다음 데이터를 가지고 메서드를 호출한다.
        sumScores = list.stream()
                .mapToInt(Articles::getScores)
                .reduce(0, (o1, o2) -> o1 + o2);
        System.out.println("집계: " + sumScores);

        // category의 질문글만 추출하여 List로 변환하기
        List<Articles> cateList = list.stream()
                .filter(articles -> articles.getCategory().equals("질문"))
                .collect(Collectors.toList());
        System.out.println(cateList);

        // category 별로 그룹화하여 scores의 평균 구하기
        Map<String, Double> cateMap = list.stream()
                .collect(Collectors.groupingBy(
                        Articles::getCategory,
                        Collectors.averagingDouble(Articles::getScores)));
        System.out.println(cateMap + " " + cateMap.get("질문"));
    }
}
