package kakao.lango.thread;

// Thread 클래스로부터 상속받는 클래스
public class ThreadEx extends Thread {
    @Override
    public void run() {
        // 1초마다 스레드 이름을 총 10번 출력하기
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(getName());
            } catch (Exception e) { }

        }
    }
}
