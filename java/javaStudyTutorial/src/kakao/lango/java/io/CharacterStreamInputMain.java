package kakao.lango.java.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CharacterStreamInputMain {
    public static void main(String[] args) {
        // 오늘 날짜로 파일명 생성하기 - 2022-12-23.log
        // 디렉토리 경로 생성하기
        String dir = "./";

        // 오늘 날짜 찾아오기
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String filename = sdf.format(date);

        // 파일 경로 만들기
        String path = String.format("%s%s%s", dir, filename, ".log");

        // 문자 단위로 버퍼를 이용해 파일 읽어오기
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while (true) {
                String str = br.readLine();
                if (str == null) {
                    System.out.println("읽은 파일이 없습니다.");
                    break;
                }
                System.out.println(str);
                // 숫자가 아닌 문자열을 숫자로 변환할 때 NumberFormatException 발생한다.
                // 웹에서 파라미터는 무조건 문자열인데, 파라미터를 숫자로 변환할 때 발생하는 예외이다.
                System.out.println(Integer.parseInt(str));
                System.out.println(Integer.parseInt(null));
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
