package kakao.lango.network.lamda;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorLamda {
    public static void main(String[] args) {
        // 데이터 정렬을 위해서 문자열 배열을 생성한다.
        String[] arr = {"이동욱", "백기선", "김영한", "최범균"};

        // Comparator 인터페이스 구현하기
//        Arrays.sort(arr, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o2.compareTo(o1);
//            }
//        });

        // 람다식으로 작성하기
        // 코드는 굉장히 간결해졌지만 가독성이 떨어진다.
        Arrays.sort(arr, (o1, o2) -> {
            return o1.compareTo(o2);
        });

        System.out.println(Arrays.toString(arr));

    }
}
