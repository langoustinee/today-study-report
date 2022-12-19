package kakao.lango.exceptionhandling;

public class DebuggingMain {
    public static void createReview(int point) {
        point += 5;
        System.out.println("현재까지 적립된 포인트는 " + point + "점 입니다.");
    }

    public static void main(String[] args) {
        createReview(5);
    }
}
