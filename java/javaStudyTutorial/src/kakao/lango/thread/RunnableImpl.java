package kakao.lango.thread;

public class RunnableImpl implements Runnable {
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
}
