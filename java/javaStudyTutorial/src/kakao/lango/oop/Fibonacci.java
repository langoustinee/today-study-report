package kakao.lango.oop;

public class Fibonacci {
    public static int loopFibo(int n) {
        // 현재 구하고자 하는 피보나치 값의 바로 앞의 값이다.
        int first = 1;
        // 현재 구하고자 하는 피보나치 값의 두번째 앞의 값이다.
        int second = 1;

        // 기본값 설정
        int fibo = 1;
        for (int i = 3; i <= n; i++) {
            fibo = first + second;
            second = first;
            first = fibo;
        }
        return fibo;
    }

    public static int recursionFibo(int n) {
        if(n == 1 || n == 2) return 1;
        return recursionFibo(n-1) + recursionFibo(n-2);
    }
}
