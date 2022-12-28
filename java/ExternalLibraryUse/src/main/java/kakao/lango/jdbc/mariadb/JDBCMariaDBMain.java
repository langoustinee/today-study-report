package kakao.lango.jdbc.mariadb;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBCMariaDBMain {
    public static void main(String[] args) {
        // 데이터베이스 접속에 필요한 정보 불러오기
        String driver = null;
        String url = null;
        String id = null;
        String password = null;
        // 불러올 파일 생성하기
        File file = new File("./db.properties");
        try (FileInputStream is = new FileInputStream(file)) {
            // 파일의 내용을 properties에 저장한다.
            Properties properties = new Properties();
            properties.load(is);
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            id = properties.getProperty("id");
            password = properties.getProperty("password");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
        // 1. 데이터베이스 드라이버 로드하기
        // 드라이버 의존성을 설정하지 않거나 클래스 이름이 틀리면 예외가 발생한다.
        try {
            Class.forName(driver);
            System.out.println("드라이버 로드 성공!");
            // 2. 데이터베이스 접속하기
            try(Connection conn = DriverManager.getConnection(url, id, password)) {
                System.out.println(conn);
            } catch(Exception e) {
                System.out.println(e.getLocalizedMessage());
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
