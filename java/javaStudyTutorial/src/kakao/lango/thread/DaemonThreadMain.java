package kakao.lango.thread;

public class DaemonThreadMain {
    public static void main(String[] args) {

        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(getName());
                    } catch (Exception e) { }
                }
            }
        };
        // 스레드를 Daemon으로 설정하기
        // 다른 스레드가 동작 중이 아니라면 자동으로 종료한다.
        t1.setDaemon(true);
        t1.start();

        // NullPointerException 발새시키기
        // Daemon이 아닌 스레드는 다른 스레드가 중지되더라도 계쏙 동작한다.
        String str = null;
//        System.out.println(str.trim());

        Thread t2 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(getName());
                    } catch (Exception e) { }
                }
            }
        };

        Thread t3 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(getName());
                    } catch (Exception e) { }
                }
            }
        };

        // 스레드의 우선순위 설정
        // 우선 순위가 높아지면 먼저 실행되거나 자주 실행될 가능성이 높아진다.
        t2.setPriority(Thread.MIN_PRIORITY);
        t3.setPriority(Thread.MAX_PRIORITY);

        t2.start();
        t3.start();
    }
}
