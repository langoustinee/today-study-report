package kakao.lango.operator;

public class ShiftOperator {
    public static void main(String[] args) {
        int x = -29;
        // 왼쪽으로 1번 밀 때 2를 곱한 것 과 같다.
        System.out.println(x << 2);
        // 오른쪽으로 1번 밀 때 2로 나눈 것과 같다.
        System.out.println(x >> 2);

        // >>>는 부호가 변경된다. 음수에서 양수로 변경됨
        System.out.println(x >>> 2);
    }
}
