package kakao.lango.operator;

public class ArithmeticOperator {
    public static void main(String[] args) {
        short s1 = 20;
        short s2 = 30;

        // short 사이의 덧셈은 50이라서 short로 처리할 수 있지만 이 문장은 에러를 발생한다.
        // Java에서 산술연산의 최소 단위는 int형인데 s1과 s2를 int로 변환하여 연산을 수행하기에 short에 저장할 수 없다.
//        short sum = s1+s2;
        int sum = s1+s2;
        System.out.println(sum);

        
        double d = 0.1;
        double tot = 0.0;
        for(int i=0; i<100; i++) {
            tot += d;
        }
        // 0.1을 100번 더했는데 10이 아니고 9.99999999999998 출력된다.
        System.out.println(tot);
        
        // mod연산에서의 나누는 수 확인
        // 0으로 나누는 것이 에러가 아닐 수 있음.
        // 연산이 안되는 경우 infinity나 Nan이 될 수 있다.
        System.out.println(5 / 0.0);
    }
}
