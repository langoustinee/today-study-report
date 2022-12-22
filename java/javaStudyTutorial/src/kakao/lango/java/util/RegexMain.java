package kakao.lango.java.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMain {
    public static void main(String[] args) {
        // 정규표현식 객체를 사용한다.
        // 문자열 배열에서 a로 시작하고 c로 끝나는 3글자를 조회하도록 만들기
        String[] arr = {"abc", "cab", "asc", "alic", "assc", "cda"};
        
        // 정규식 생성하기
        // a로 시작하고 아무글자 하나 존재하고 c로 끝나는 패턴 생성한다.
        Pattern p = Pattern.compile("^a.c$");
        for(String str : arr) {
            // 정규식 패턴과 일치하는지를 확인한다.
            Matcher matcher = p.matcher(str);
            System.out.println(str + ": " + matcher.find());
        }
    }
}
