package kakao.lango.consoleStatement;

import java.util.Scanner;

public class PassIf {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in);) {
            /*
            System.out.println("점수를 입력하세요.");
            int score = sc.nextInt();

            // else문에는 웬만하면 예외처리를 작성하자.
            if(score <= 100 && score >= 90) System.out.println("A학점 입니다.");
            else if(score < 90 && score >= 80) System.out.println("B학점 입니다.");
            else if(score < 80 && score >= 70) System.out.println("C학점 입니다.");
            else if(score < 70 && score >= 60) System.out.println("D학점 입니다.");
            else if(score < 60 && score >= 0) System.out.println("F학점 입니다.");
            else System.out.println("잘못된 점수를 입력하였습니다.");
            */

            // id와 pwd를 받아서 id가 root이고 qwd가 1234리면 로그인 성공
            System.out.println("아이디를 입력하세요.");
            String id = sc.nextLine();
            System.out.println("비밀번호를 입력하세요.");
            String pwd = sc.nextLine();
            
            // 문자열은 생성 방법에 따라 다른 인스턴스가 될 수 있기에 == 연산자로 비교하면 안된다.
            // equals 메서드를 이용하여 문자열 동일성 여부를 비교해야 한다.
            if(id.equals("root") && pwd.equals("1234")) System.out.println("Login Success!");
            else System.out.println("Login Fail ...");

        }
        catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
