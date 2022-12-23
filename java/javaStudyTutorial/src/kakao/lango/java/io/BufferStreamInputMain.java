package kakao.lango.java.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Arrays;

public class BufferStreamInputMain {
    public static void main(String[] args) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("./sample2.txt"))) {
            // 파일에서 읽을 수 있는 크기로 바이트 배열 생성하기
//            byte[] b = new byte[bis.available()];
//            // 읽을 파일이 없을 때까지 반복하고 종료하기
//            while (bis.read(b) > 0) {
//                System.out.println("읽은 데이터 출력: " + Arrays.toString(b));
//                // 문자열로 출력하기
//                System.out.println("읽은 데이터 출력: " + new String(b));
//            }
            
            // 파일을 나눠서 읽어오기
            // 보통 웹에서 파일을 다운로드 받을 때 사용한다.
            while (true) {
                // 16바이트 단위로 읽어오기
                // 보통은 128 또는 128의 배수를 많이 이용한다.
                byte[] b = new byte[1024];
                int len = bis.read(b, 0, b.length);
                if (len <= 0) {
                    break;
                }
                // 받을 내용을 가지고 작업할 내용 작성한다.
                // 만약 파일 다운로드라면 파일에 기록하면 된다.
                // 만약 문자열이라면 하나로 모아서 읽어야 한다.
                System.out.println(new String(b).trim());
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
