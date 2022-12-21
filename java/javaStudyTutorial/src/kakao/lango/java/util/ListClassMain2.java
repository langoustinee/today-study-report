package kakao.lango.java.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListClassMain2 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("랑고스틴");
        list.add("둘리");
        list.add("망고링고");
        list.add("영원은없어");
        list.add("받아들여가진걸");

        // list 순회하기
        for(String temp : list) {
            System.out.println(temp);
        }

        // list 정렬하기
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // 오름차순 정렬하기
                return o1.compareTo(o2);
            }
        });
        System.out.println(list);
    }
}
