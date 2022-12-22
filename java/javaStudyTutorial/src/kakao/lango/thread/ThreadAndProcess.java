package kakao.lango.thread;

public class ThreadAndProcess {
    public static void main(String[] args) {
        // 스레드를 사용하지 않은 경우를 보자.
        // 하나의 작업이 다 끝난 후에야 다음 작업을 실행한다.
//        new Thread() {
//            public void run() {
//                for(int i=0; i<10; i++) {
//                    try {
//                        Thread.sleep(1000);
//                        System.out.println(i);
//                    } catch (Exception e) { }
//                }
//            }
//        }.run();
//
//        new Thread() {
//            public void run() {
//                for(int i=0; i<10; i++) {
//                    try {
//                        Thread.sleep(1000);
//                        System.out.println(i);
//                    } catch (Exception e) { }
//                }
//            }
//        }.run();

        // 스레드로 작업을 실행하기
        new Thread() {
            public void run() {
                for(int i=0; i<10; i++) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(i);
                    } catch (Exception e) { }
                }
            }
        }.start();

        new Thread() {
            public void run() {
                for(int i=0; i<10; i++) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(i);
                    } catch (Exception e) { }
                }
            }
        }.start();
    }
}
