package kakao.lango.java.io;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesMain {
    public static void main(String[] args) {
        // 현재 디렉토리의 위치 찾기
//        File f = new File("./");
//        System.out.println(f.getAbsolutePath());

        // 설정 파일의 경로를 지정한다.
        File file = new File("config.properties");
        try (FileInputStream fis = new FileInputStream(file)) {
            // 설정 파일을 불러오기 위한 준비하기
            Properties properties = new Properties();
            properties.load(fis);

            // 읽어오기
            System.out.println(properties.getProperty("url"));
            System.out.println(properties.getProperty("id"));
            System.out.println(properties.getProperty("password"));
            System.out.println(properties.getProperty("nickname"));
            System.out.println(properties.getProperty("address"));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
