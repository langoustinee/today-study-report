package kakao.lango.thread;

public class ThreadCreateMain {
    public static void main(String[] args) {
        // 클래스를 상속받았을 때 대부분의 경우는 변수를 만들 때 상위클래스명을 사용해야 한다.
        Thread t1 = new ThreadEx();
        t1.start();

        // anonymous class로 Runnable 사용하기
        // 인터페이스 이기에 start()가 없다.
        // run 메서드 안에는 오래 걸리는 작업들을 작성해야 한다.
        // 시분할 작업이란 일정한 시간마다 작업을 실행하는 것이다.
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // 1초마다  i를 총 10번 출력하기
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(i);
                    } catch (Exception e) {
                    }
                }
            }
        };
        Thread t2 = new Thread(runnable);
        t2.start();
    }
}
