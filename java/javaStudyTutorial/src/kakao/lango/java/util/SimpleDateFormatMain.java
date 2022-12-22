package kakao.lango.java.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatMain {
    public static void main(String[] args) {
        // 날짜 형식을 맞춰서 현재 날짜 출력하기
        SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일 EEEE a hh:mm:ss");
        System.out.println(date.format(new Date()));
    }
}
