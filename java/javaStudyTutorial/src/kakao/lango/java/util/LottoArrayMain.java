package kakao.lango.java.util;

import java.util.Arrays;
import java.util.Scanner;

public class LottoArrayMain {
    public static void main(String[] args) {
        /*
         * 로또 번호 생성기 만들기
         * 1~45까지의 숫자 6개를 입력받아서 저장한 후 출력한다.
         * 입력시 1~45사이의 숫자가 아니면 다시 입력하도록 한다.
         * 중복되는 숫자를 입력하면 다시 입력하도록 한다.
         * 데이터 출력시에는 정렬하여 출력한다.
        */

        /* 배열을 활용한 풀이 */
        // 입력받기 위한 스캐너 인스턴스를 생성한다.
        Scanner sc = new Scanner(System.in);
        // 6개의 정수를 저장할 배열을 생성한다.
        int[] lotto = new int[6];
        int len = lotto.length;
        for(int i=0; i<len; i++) {
            try {
                System.out.println("로또 번호를 입력하세요.");
                lotto[i] = sc.nextInt();
                // 1~ 45 사이의 숫자만 입력받도록 하기
                if(lotto[i] < 1 || lotto[i] > 45) {
                    System.out.println("1부터 45 사이의 로또 번호를 입력하세요.");
                    i--;
                    // 중복검사를 하지 않고 다음 반복으로 넘어가기 위해서 continue를 수행한다.
                    continue;
                }
                // 데이터 중복 검사하기 - 첫번쨰 데이터부터 현재 데이터 앞까지 비교한다.
                // 중복 발생여부를 체크할 변수 만들기
                boolean flag = false;
                for(int j=0; j<i; j++) {
                    // 2개의 데이터가 같다면 - 중복된 숫자가 있다면
                    if(lotto[i] == lotto[j]) {
                        // 중복된 숫자가 있다면 flag 상태 변경
                        flag = true;
                        break;
                    }
                }

                // 중복된 경우
                // if(flag == true)가 if(flag)보다 가독성이 좋다.
                if(flag == true) {
                    System.out.println("중복된 로또 번호가 입력되었습니다. 다시 입력해주세요.");
                    i--;
                }

            } catch (Exception e) {
                i--; // 예외 발생시 인덱스를 뒤로하여 숫자 입력 기회를 무효화 시킨다.
                sc.nextLine();
                System.out.println("숫자를 다시 입력하세요.");
            }
        }
        // 배열 데이터 오름차순 정렬하기
        Arrays.sort(lotto);
        // 배열 데이터 출력하기
        System.out.println(Arrays.toString(lotto));
        // 스캐너 정리하기
        sc.close();
    }
}
