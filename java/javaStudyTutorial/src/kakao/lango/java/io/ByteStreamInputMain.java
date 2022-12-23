package kakao.lango.java.io;

import java.io.FileInputStream;
import java.util.Arrays;

public class ByteStreamInputMain {
    public static void main(String[] args) {
        // 파일의 내용을 바이트 단위로 읽기
        try(FileInputStream fis = new FileInputStream("./sample.txt")) {
            while (true) {
                // 읽을 수 있는 크기로 바이트 배열을 생성하기
                byte[] b = new byte[fis.available()];
                int len = fis.read(b);
                if(len <= 0) {
                    System.out.println("읽은 데이터가 없습니다!");
                    break;
                }else {
                    // 읽어온 파일 데이터가 텍스트일 때
                    // 숫자 배열을 추력하기
                    System.out.println("읽은 데이터를 출력합니다. " + Arrays.toString(b));
                    // 숫자 배열을 문자열로 변환하여 출력하기
                    System.out.println("읽은 데이터를 출력합니다. " + new String(b));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
