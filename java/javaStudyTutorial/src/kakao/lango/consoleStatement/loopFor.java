package kakao.lango.consoleStatement;

import java.util.Arrays;

public class loopFor {
    public static void main(String[] args) {

        int n = 10;
        for (int i = 0; i < n; i++) {
            System.out.println(i);
        }

        for (int i = 0, j = 0; i < n && j < 7; i++, j += 2) {
            System.out.println(i);
            System.out.println(j);
        }

        String[] arr = {"Java", "Python", "JavaScrpt", "Go", "Rust"};

        for (String str : arr) {
            System.out.println(str);
        }

        // 아래와 같이 메서드 내에서 만든 변수를 for문 안에서 다시 만들려고 하면 에러 발생한다.
//        int x = 10;
//        for(int x=0; x<10; x++) {
//            System.out.println(x);
//        }

        // 아래와 같이 제어문 내에서 만든 변수는
        // 몇번을 다시 만들어도 제어문 수행이 끝나면 소멸하기에 상관없다.
        for(int x=0; x<10; x++) {
            System.out.println(x);
        }
        for(int x=0; x<10; x++) {
            System.out.println(x);
        }

        for(int i=0; i<10; i++) {
            System.out.println(i);
            // break나 continue가 반복문 안에서 조건없이 사용된다면 dead code가 만들어지기에 경고 발생
//            break;
            // 조건이 있다면 경고 없어짐
            // 뒤 코드를 싱행할 필요가 없다면 return으로 대체 가능
            if(i > 3) break;
        }

        // 무한 반복 만들기
//        while (true) {
//        }

//        do {
//        }
//        while (true);

//        for(;;)

        // 반복문에 레이블 생성하기
//        label : for(int i=0; i<10; i++) {
//            System.out.println(i);
//            if(i == 3) {
//                break label;
//            }
//        }

        // 별 찍기
//        for(int i=0; i<=25; i++) {
//            System.out.println("*");
//            if(i % 5 == 0) {
//                System.out.println();
//            }
//        }

        // 배열의 크기를 먼저 선언하기
//        int[] array = new int[5];

        // 배열의 데이터를 순회하기
        String[] alphas = {"a", "b", "c", "d", "e"};
        int length = alphas.length;
        for(String alpha : alphas) {
            System.out.println(alpha);
        }

        // 배열 생성후 데이터 대입하기
        // 1~5까지 빈 배열에 대입
        int[] numbers = new int[5];
        for(int i=1; i<=numbers.length; i++) {
            numbers[i-1] = i;
        }
        System.out.println(Arrays.toString(numbers));

        // NullPointerException은 null이기에 문제인 것이 아니라
        // null 상태인 값을 가져오려고 할 때 예외가 발생하는 것이다.
        // 
        
        try {
            int[] temp= null;
            System.out.println(temp[0]);    
        }catch (Exception e) {
        }

        System.out.println("종료");
        System.out.println(numbers[20]);

        


    }
}