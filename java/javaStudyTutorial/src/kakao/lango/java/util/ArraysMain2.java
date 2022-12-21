package kakao.lango.java.util;

import java.util.Arrays;
import java.util.Comparator;

public class ArraysMain2 {
    public static void main(String[] args) {
        String[] names = {"lango", "hoihoi", "joon", "hong"};
        System.out.println(Arrays.toString(names));

        // 복사본 만들기
        String[] copy = Arrays.copyOf(names, names.length);

        // 오름차순 정렬하기
        Arrays.sort(copy);
        System.out.println(Arrays.toString(copy));
        Arrays.sort(copy, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // 문자열 배열을 정렬할 떄는 뺼셈이 아니라 compareTo 메서드로 비교한다.
                // 오름차순 정렬하기
                //return o1.compareTo(o2);
                // 내림차순 정렬하기
                return o2.compareTo(o1);
            }
        });
        System.out.println(Arrays.toString(copy));
    }
}
