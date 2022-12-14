package kakao.lango.operator;

public class Unary {
    public static void main(String[] args) {
        int a = 10;
        int b = -10;

        // 정수 데이터가 2진수로 저장된 구조를 출력한다.
        System.out.println("a: " + Integer.toBinaryString(a));
        System.out.println("b: " + Integer.toBinaryString(b));

        // 1의 보수 구하기 + 21
        System.out.println("~b: " + Integer.toBinaryString(~b));
        System.out.println("~a: " + Integer.toBinaryString(~a));

        int n = 20;
        // 명령에 먼저 사용되기에 20 출력
        System.out.println(n++);
        // 증감한 뒤 명령에 사용되기에 22 출력
        System.out.println(++n);

    }
}