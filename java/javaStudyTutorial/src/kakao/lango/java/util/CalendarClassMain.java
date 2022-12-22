package kakao.lango.java.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarClassMain {
    public static void main(String[] args) {
        // Calendar로 원하는 날짜를 생성하고 Date로 변환하기
        Calendar cal = new GregorianCalendar(1995, 9, 5);
        System.out.println(cal);

        Date date = new Date(cal.getTimeInMillis());
        System.out.println(date);
    }
}
