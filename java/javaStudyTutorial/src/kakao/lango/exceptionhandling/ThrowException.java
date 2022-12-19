package kakao.lango.exceptionhandling;

public class ThrowException {
    // exception 메서드를 다른 곳에서 호출할 때는 ArithmeticException을 핸들링 해야한다.
    // 실제로는 이 메서드 안에서 ArithmeticException이 발생할 만한 코드가 있어야 한다.
    public static void exception(int kor, int eng) throws ArithmeticException {
        if(kor > 100 || eng > 100) {
            // 강제로 예외를 발생시킨다.
            throw new ArithmeticException("점수는 100보다 작거나 같아야 한다.");
        }
        double avg = (kor + eng) / 2;
        System.out.println(avg);
    }
    public static void main(String[] args) throws InterruptedException {
        exception(40, 120);

        // Thread 클래스의 sleep 메서드는 지정한 시간동안 현재스레드의 수행을 중지하는 역할을 한다.
        // 이 메서드를 사용할 떄는 InterruptedException 예외를 처리해야 사용할 수 있다.
        // 3초동안 수행 중지
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            System.out.println(e.getLocalizedMessage());
//        }
        // main 메서드에서 thorws를 던지면 내부에서 예외처리를 하지 않아도 무방하다.
        Thread.sleep(3000);
    }
}
