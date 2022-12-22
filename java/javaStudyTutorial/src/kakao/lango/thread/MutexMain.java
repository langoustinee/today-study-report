package kakao.lango.thread;

// 스레드 작업을 위한 클래스
class ShareData implements Runnable {
    // 합계를 저장할 속성
    private int sum;
    // 합계를 구할 때 사용할 인덱스
    private int idx;

    public int getSum() {
        return sum;
    }

    // idx의 값을 1씩 증가시키면서 sum에 더해줄 메서드 생성하기
    // 메서드에 synchronized를 붙이지 않는다.
//    private synchronized void sum() {
    private void sum() {
        for(int i = 0; i < 1000; i++) {
            // 이 영역 안에서는 다른 스레드가 this를 사용할 수 없다.
            synchronized (this) {
                idx++;
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                }
                sum += idx;
            }
        }
    }

    @Override
    public void run() {
        sum();
    }
}
public class MutexMain {
    public static void main(String[] args) {
        ShareData data = new ShareData();

        Thread t1 = new Thread(data);
        t1.start();

        Thread t2 = new Thread(data);
        t2.start();

        try {
            Thread.sleep(5000);
            System.out.println(data.getSum());
        } catch (Exception e) { }

    }
}
