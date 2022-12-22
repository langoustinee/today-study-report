package kakao.lango.java.util;

import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesMain {
    public static void main(String[] args) {
        // 인스턴스를 1개만 만들 때 참조하는 이름은 클래스명을 그대로 작성하고 첫글자만 소문자로 변경한다.
        // Spring이 bean을 만들 때 사용하는 방법이다.
        Properties properties = new Properties();

        // 데이터 저장하는데 키와 값은 문자열만 가능하다.
        properties.setProperty("title", "설정의 제목입니다.");
        properties.setProperty("content", "설정의 내용입니다.");

        // 읽어오기
        System.out.println(properties.getProperty("title"));
        System.out.println(properties.getProperty("content"));
        // 존재하지 않는 key를 호출할 경우 null이 출력된다.
        System.out.println(properties.getProperty("comment"));

        // 반복자 사용하여 key, value 값 가져오기
        Enumeration<Object> keys = properties.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement().toString();
            System.out.println(key + " " + properties.getProperty(key));
        }

        // 파일로 저장하기
        try {
            properties.store(new FileOutputStream("./setting.properties"), "텍스트로 저장하기");
            properties.storeToXML(new FileOutputStream("./setting.xml"), "XML로 저장하기");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

    }
}
