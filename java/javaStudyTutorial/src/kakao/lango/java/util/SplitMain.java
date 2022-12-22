package kakao.lango.java.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplitMain {
    public static void main(String[] args) {
        // 관계형 데이터베이스에서는 하나의 기본 키를 설정하면 이 기본 키에 배열로 매핑되는 데이터는 삽입할 수 없다.
        // 제 1 정규형(NF, Normal Form): 모든 속성의 값은 원자값이어야 한다.
        // 원자값(Atomic Value)이란 더 이상 분해할 수 없는 값이다.
        // 하나의 게시글에 여러 개의 첨부파일을 삽입하는 경우 첨부 파일 테이블을 별도로 만든다.
        // 이렇게 별도의 테이블로 만들면 게시글을 가져와서 첨부파일명을 출력하기 위해 join을 해야한다.
        // 관계형 데이터베이스에서 가장 많은 시간을 소비하는 것이 join 이기에 join 시간을 아끼고자 한다면 첨부파일 이름들을 하나로 묶어서 저장해도 된다.

        // ArrayList는 데이터를 물리적으로 연속해서 저장한다.
        // LinkedList보다 조회속도는 빠르다.
        List<String> list = new ArrayList<>();
        list.add(null);
        list.add("uuid_1.jpg");
        list.add("uuid_1.jpg,uuid_2.jpg,uuid_3.jpg");

        // list 순회하기
        for(String temp : list) {
            if (temp == null) {
                System.out.println("첨부파일이 없습니다.");
            } else {
                String[] imsi = temp.split(",");
                System.out.println(Arrays.toString(imsi));
            }
        }

        String[] post1 = {"uuid_1.jpg"};
        String[] post2 = {"uuid_1.jpg,uuid_2.jpg,uuid_3.jpg"};
        String[] post3 = null;

        if (post3 == null) {
            System.out.println("default.jpg");
        }

        if (post1 != null && post2.length == 1) {
            System.out.println(post2[0]);
        }
        else {
            String post = post2[0];
            for (int i = 0; i < post2.length; i++) {
                post = post + "," + post2[i];
            }
            System.out.println(post);
        }





    }
}
