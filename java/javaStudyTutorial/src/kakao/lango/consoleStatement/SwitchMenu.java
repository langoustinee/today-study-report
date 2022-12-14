package kakao.lango.consoleStatement;

import java.util.Scanner;

public class SwitchMenu {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in);) {

            // 상수로 활용할 때는 일반변수로 사용할 수 없고
            // 반드시 final로만 사용해야 한다.
            final int JAJJANG = 1;
            final int JJAMBONG = 2;
            final int TANG = 3;

            System.out.println("메뉴를 입력하세요. [1, 2, 3]");
            int menu = sc.nextInt();

            switch (menu) {
                case JAJJANG:
                    System.out.println("짜장면을 주문하셨습니다.");
                    break;
                case JJAMBONG:
                    System.out.println("짬뽕을 주문하셨습니다.");
                    break;
                case TANG:
                    System.out.println("탕수육을 주문하셨습니다.");
                    break;
                default:
                    System.out.println("기본 반찬을 세팅하겠습니다.");
                    break;
            }

        }
        catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
