package kakao.lango.java.io;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CharacterStreamOutputMain {
    public static void main(String[] args) {
        // 오늘 날짜로 파일명 생성하기 - 2022-12-23.log
        // 디렉토리 경로 생성하기
        String dir = "./";

        // 오늘 날짜 찾아오기
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String filename = sdf.format(date);

        // 파일 경로 만들기
        // 문자열을 + 연산으로 결합할 경우 메모리 낭비가 될 수 있기에 지양해야 한다.
//        String path = dir + filename + ".log";
        String path = String.format("%s%s%s", dir, filename, ".log");

        // 문자 단위로 버퍼를 이용하여 기록하기
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, true))) {
            pw.println("오늘 점심 메뉴는 ???");
            pw.println("삼산회관의 김치구이 입니다~!");
            pw.println("모두 소리질러 ㅓㅓㅓ 111 🎉");
            pw.flush();

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
