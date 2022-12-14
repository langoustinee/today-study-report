package kakao.lango.basic;

import java.util.Arrays;

public class ConsoleDisplay {
    public static void main(String[] args) {
        // Console에 메시지 출력
        System.out.println("Message"+10);

        Integer i = 120;
        int[] arr = {10, 20, 30};
        char[] arr2 = {'1', '2', '3'};

        // 둘의 출력은 같음
        System.out.println(i + " " + i.toString());

        // 배열은 toString을 재정의해두지 않아 해시코드가 출력된다.
        System.out.println(Arrays.toString(arr) + " " + arr2);

        int a = 10;
        int b = a;
        a = 11;
        System.out.println(a + " " + b);

        int[] arr3 = {1,2,3};
        int[] arr4 = arr3;

        arr3[0] = 10;
        System.out.println(Arrays.toString(arr3) + " " + Arrays.toString(arr4));

    }
}
