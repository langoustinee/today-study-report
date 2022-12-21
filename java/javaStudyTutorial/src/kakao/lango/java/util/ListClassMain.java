package kakao.lango.java.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListClassMain {
    public static void main(String[] args) {
        // 정수를 저장하기 위한 ArrayList 만들기
        List<Integer> al = new ArrayList<>();

        // 정수를 저장하기 위한 LinkedList 만들기
        LinkedList<Integer> li = new LinkedList<>();

//        for(int i=0; i<100000; i++) {
//            al.add(i);
//            li.add(i);
//        }

        al.add(1);
        al.add(3);
        li.add(1);
        li.add(3);

        long start;
        long end;

//        start = System.currentTimeMillis();
//        for(int i=0; i<100000; i++) {
//            Integer integer = al.get(i);
//        }
//        end = System.currentTimeMillis();
//        System.out.println("ArrayList 조회 시간: " + (end-start));
//
//        start = System.currentTimeMillis();
//        for(int i=0; i<100000; i++) {
//           Integer integer = li.get(i);
//        }
//        end = System.currentTimeMillis();
//        System.out.println("LinkedList 조회 시간: " + (end-start));

        start = System.currentTimeMillis();
        for(int i=0; i<100000; i++) {
            al.add(1, 2);
        }
        end = System.currentTimeMillis();
        System.out.println("ArrayList 삽입 시간: " + (end-start));

        start = System.currentTimeMillis();
        for(int i=0; i<100000; i++) {
            li.add(1, 2);
        }
        end = System.currentTimeMillis();
        System.out.println("LinkedList 삽입 시간: " + (end-start));

    }
}
