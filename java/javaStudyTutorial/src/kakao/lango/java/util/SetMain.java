package kakao.lango.java.util;

import java.util.*;

public class SetMain {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        // 데이터 추가하기
        set.add("재즈");
        set.add("발라드");
        set.add("힙합");
        // 동일한 데이터를 삽입 - equals 메서드로 비교하여 true이면 삽입하지 않는다.
        // SEt은 데아터 삽입에 실패하면 false를 리턴하고 성공하면 true를 리턴한다.
        set.add("재즈");
        System.out.println(set);
        set.add("동요");
        System.out.println(set);
    }
}
