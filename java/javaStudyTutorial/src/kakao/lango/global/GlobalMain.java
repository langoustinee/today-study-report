package kakao.lango.global;

public class GlobalMain {
    public static void main(String[] args) {
        // public 클래스의 public static 속성은 어느 곳에서나 호출이 가능하다.
        GlobalData.reviewPoint = 1;
        System.out.println("GlobalData.reviewPoint: " + GlobalData.reviewPoint);
    }
}
