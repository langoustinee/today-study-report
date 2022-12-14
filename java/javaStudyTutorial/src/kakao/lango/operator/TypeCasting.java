package kakao.lango.operator;

public class TypeCasting {
    public static void main(String[] args) {
        double d = 37.4;
        // 실수를 정수에 대입하면 실수가 정수보다 크기 때문에 에러가 발생한다.
//        int n = d;
        // 강제 형 변환을 이용해 실수를 정수에 대입할 수 있다.
        // 실수를 정수로 형 변환하면 소수가 버려진다.
        int n = (int) d;
        System.out.println(n);

        short s1 = 30000;
        short s2 = 30000;
        short sum = (short) (s1 + s2);
        System.out.println(sum);

        // 정수 2개의 평균
        int a = 91;
        int b = 90;

        // 정수를 가지고 산술연산을 할 경우 정수의 결과만 얻을 수 있다.
//        double avg = (a+b)/2;
        // 강제 형 변환을 통해 실수 결과 평균값을 출력
        double avg = ((double)a+b)/2;
        System.out.println(avg);
    }

}
