package kakao.lango.java.io;

import java.io.File;
import java.util.Date;

public class FileInformationMain {
    public static void main(String[] args) {
        // FIle 객체 생성하기
        File f = new File("C:\\Users\\user\\Documents\\교육일지\\1114.txt");

        // 파일의 존재여부 확인하기
        if (f.exists() == true) {
            System.out.println("파일의 크기: " + f.length());
            System.out.println("파일의 마지막 수정 날짜: " + f.lastModified());
            Date date = new Date(f.lastModified());
            System.out.println("파일의 마지막 수정 날짜: " + date);
        }
        else System.out.println("파일이 존재하지 않습니다.");

    }
}
