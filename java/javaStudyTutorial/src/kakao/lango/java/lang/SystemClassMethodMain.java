package kakao.lango.java.lang;

public class SystemClassMethodMain {
    public static void main(String[] args) {
        // 운영체제 이름을 가져오기
        String os = System.getProperty("os.name");
        System.out.println(os);

        // Java Version 확인하기
        String version = System.getProperty("java.version");
        System.out.println(version);

        // 작성한 코드의 실행 시간 구하기
        long start = System.currentTimeMillis();
        for(int i=0; i<100000000; i++) {

        }
        long end = System.currentTimeMillis();
        System.out.println("실행 시간은 " + (end-start) + "밀리초 입니다.");
    }
}
