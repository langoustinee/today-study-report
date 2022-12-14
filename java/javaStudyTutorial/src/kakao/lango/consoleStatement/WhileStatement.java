package kakao.lango.consoleStatement;

public class WhileStatement {
    public static void main(String[] args) {
        int num = 1;
        int sum = 0;
        
        // while문
        // num이 10보다 작거나 같을 때까지 while의 수행내용을 반복한다.
        // num이 10이 된다면 표현식이 false가 되기에 반복문 탈출.
//        while (num <= 10) {
//            sum += num;
//            num++;
//        }

        // do ~ while
        do {
            sum += num;
            num++;
        }
        while (num <= 10);

        System.out.println(sum);
    }
}
