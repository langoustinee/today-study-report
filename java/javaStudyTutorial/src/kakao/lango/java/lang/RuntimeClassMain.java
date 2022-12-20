package kakao.lango.java.lang;

import java.io.IOException;

public class RuntimeClassMain {
    public static void main(String[] args) {
        // Runtime 클래스의 인스턴스 생성하기
        Runtime r1 = Runtime.getRuntime();
        Runtime r2 = Runtime.getRuntime();

        // 싱글톤 패턴으로 디자인되어 해시코드가 동일함을 확인할 수 있다.
        System.out.println(System.identityHashCode(r1));
        System.out.println(System.identityHashCode(r2));

        // 프로세스 실행
        // windows에서는 프로세스 이름만 사용하면 된다.
        // amc은 open 파일경로로 사용해야 한다.
        try {
            r1.exec("notepad");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
