package kakao.lango.java.lang;

import java.math.BigDecimal;

public class WrapperClassMain {
    public static void main(String[] args) {
        // Wrapper 클래스 사용하기
        Double d = new Double(3.14);
        // 기본형을 대입하면 new Doble(13,67)로 변환하여 수행한다.
        d = 13.67;
        // toString이 재정의되어있어 출력은 참조로 가능하다.
        System.out.println(d);

        // 래퍼클래스를 기본형 데이터로 변환하기
        double n = d;
        System.out.println(n);

        double d1 = 1.60000000000000000;
        double d2 = 0.10000000000000000;
        // d1+d2의 값이 1.7이 되지 않음
        System.out.println(d1 + d2);

        BigDecimal b1 = new BigDecimal("1.60000000000000000");
        BigDecimal b2 = new BigDecimal("0.10000000000000000");
        // b1+b2가 1.7이 나옴
        System.out.println(b1.add(b2));
    }
}
