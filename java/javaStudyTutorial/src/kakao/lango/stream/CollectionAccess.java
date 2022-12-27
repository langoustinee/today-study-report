package kakao.lango.stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class CollectionAccess {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("JavaScropt");
        list.add("Python");
        list.add("GO");

        // 반복문 이용하기
        int len = list.size();
        for (int i = 0; i < len; i++) {
            System.out.print(list.get(i) + "\t");
        }
        System.out.println();

        // iterator 이용하기
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "\t");
        }
        System.out.println();

        // 빠른 열거 사용하기
        for (String str : list) {
            System.out.print(str + "\t");
        }
        System.out.println();

        // Stream API 이용하기
        // 내부 반복자를 사용하기에 가장 빠르다.
        Stream<String> stream = list.stream();
        // ::을 사용하면 /t으로 구분할 수 없다.
        // stream.forEach(System.out::print);
        // lamda식으로 작성하면 /t으로 구분할 수 있다.
        stream.forEach(temp -> System.out.print(temp + "\t"));
    }
}
