package kakao.lango.consoleStatement;

import java.util.Scanner;

public class ConsoleInput {
    public static void main(String[] args) {
        /*
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {

            System.out.println("이름를 입력하세요.");
            String name = br.readLine();
            System.out.println(name);

            System.out.println("나이를 입력하세요.");
            int age = Integer.parseInt(br.readLine());
            System.out.println(age);

        }catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        */

        // 본래 scanner 사용시 sc.close()를 해야한다.
        // try catch문을 통해 생략할 수 있다.
        try(Scanner sc = new Scanner(System.in);) {
            System.out.println("가격을 입력하세요.");
            int price = sc.nextInt();
            System.out.println(price);

            // 숫자를 먼저 입력받는다면 Enter값을 입력으로 판단하기에 다음 문자열 입력을 할 수 없게된다.
            // nextLine으로 Enter 입력을 제거할 수 있다.
            sc.nextLine();

            System.out.println("상품명을 입력하세요.");
            String product = sc.nextLine();
            System.out.println(product);



        }catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }


//        try {
//            String str = br.readLine();
//        }catch(IOException e) {
//            e.printStackTrace();
//        }

    }
}
