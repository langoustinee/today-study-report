package kakao.lango.network.lamda;

public class RunnableLamda {
    public static void main(String[] args) {

        // Runnable 인터페이스를 이용한 스레드 생성하기
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 1초에 한번씩 문자열을 10번 출력하기
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                        System.out.println("일반적인 방식");

                    } catch (Exception e) {
                        System.out.println(e.getLocalizedMessage());
                    }
                }
            }
        }).start();

        // 람다식으로 스레드 생성하기
        new Thread(() -> {
            // 1초에 한번씩 문자열을 10번 출력하기
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println("람다를 이용한 방식");

                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
        }).start();
    }
}
