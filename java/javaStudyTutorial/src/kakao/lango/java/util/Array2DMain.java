package kakao.lango.java.util;

import java.util.HashMap;
import java.util.Map;

public class Array2DMain {
    public static void main(String[] args) {
        // 배열의 배열 만들기 - 2차원 배열: Matrix
        String[] posts = {"스프링 완전정복", "모던 인 자바", "생활 코딩"};
        String[] authors = {"김영한", "고슬링", "백기선"};
        String[] stacks = {"Java", "JPA", "Spring"};

        // 2차원 배열 생성하기
        String[][] arr = {posts, authors, stacks};

        int i=0;
        for(String[] temp : arr) {
            if(i == 0) System.out.print("posts: ");
            else System.out.print("authors: ");
            i++;
            for(String str : temp) {
                System.out.print(str + "\t");
            }
            System.out.println("");
        }

        // 2차원 배열 대신에 Map의 배열 - Table
        Map<String, Object> hm1 = new HashMap<>();
        hm1.put("title", "스프링 완전정복");
        hm1.put("author", authors);

        Map<String, Object> hm2 = new HashMap<>();
        hm2.put("title", "모던 인 자바");
        hm2.put("author", authors);

        Map<String, Object> hm3 = new HashMap<>();
        hm3.put("title", "생활코딩");
        hm3.put("author", authors);

        // 새로운 map을 추가했다고 해서 View를 바꿀 필요가 없다.
        Map[] v = {hm1, hm2, hm3};
        for(Map map : v) {
            System.out.print(map.get("title") + "\t");
            String[] temp = (String[]) map.get("author");
            for(String author : temp) {
                System.out.print(author + "\t");
            }
            System.out.println();
        }
    }
}
