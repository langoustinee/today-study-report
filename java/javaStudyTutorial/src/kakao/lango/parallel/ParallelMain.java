package kakao.lango.parallel;

import java.util.Arrays;
import java.util.List;

public class ParallelMain {
    public static void main(String[] args) {
        // 정수 리스트 생성하기
        List<Integer> list = Arrays.asList(2, 3, 4, 1, 0, 5, 8, 9, 6, 7);

        // 일반 스트림으로 1초씩 쉬면서 출력하기
        long start = System.currentTimeMillis();
        list.stream().forEach(num -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        });
        long end = System.currentTimeMillis();
        System.out.println("소요 시간: " + (end - start));

        // 병렬 스트림으로 1초씩 쉬면서 출력하기
        // 중간연산에 parallel() 추가하면 된다.
        start = System.currentTimeMillis();
        list.stream().parallel().forEach(num -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        });
        end = System.currentTimeMillis();
        System.out.println("소요 시간: " + (end - start));
        // 코어 수 확인하기
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
