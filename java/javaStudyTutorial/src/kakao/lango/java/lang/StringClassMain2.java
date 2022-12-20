package kakao.lango.java.lang;

import java.io.UnsupportedEncodingException;

public class StringClassMain2 {
    public static void main(String[] args) {
        // 여러 데이터를 가지고 하나의 문자열을 생성하기
        double lat = 12.425;
        double lng = 24.3271;

        // 위 데이터를 가지고 문자열을 만들기
        String location1 = "위도:" + Double.toString(lat) + " 경도:" + Double.toString(lng);
        System.out.println(location1);
        String location2 = String.format("위도:%.3f 경도:%.3f\n", lat, lng);
        System.out.println(location2);

        // 바이트배열로 문자열을 변환하기
        String str = "Hello World!";
        try {
            // 바이트 배열로 문자열을 변환할 때 MS949를 이용한다.
            // 동일한 프로그램이 아닌 시스템과 채팅을 할 떄에는 문자열을 직접 전송하지 않고 바이트배열을 만들어서 전송한다.
            byte[] bytes = str.getBytes("UTF-8");

            // byte배열을 문자열로 변환하기
            String result = new String(bytes, "MS949");
            // 문자열이 깨진다면 인코딩 방식을 확인하고 변경해야 한다.
            System.out.println(result);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }
}
