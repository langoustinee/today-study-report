package kakao.lango.network;

import java.net.InetAddress;
import java.util.Arrays;

public class IPINformation {
    public static void main(String[] args) {
        try {
            // 자신 컴퓨터의 IP 정보 확인하기
            InetAddress localhost = InetAddress.getLocalHost();
            System.out.println(localhost);

            // 도메인을 가지고 IP 정보를 확인하기
            InetAddress[] naver = InetAddress.getAllByName("www.naver.com");
            System.out.println(Arrays.toString(naver));

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            // 오늘 날짜로 텍스트 파일에 예외 내용을 로깅하기
        }
    }
}
