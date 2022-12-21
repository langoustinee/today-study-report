package kakao.lango.java.util;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class LottoSetMain {
    public static void main(String[] args) {
        /* TreeSet을 활용한 풀이 */

        // 입력받기 위한 스캐너 인스턴스를 생성한다.
        Scanner sc = new Scanner(System.in);

        // 숫자 6개를 저장할 공간을 생성한다.
        // 중복된 데이터를 저장하지 않고 데이터를 정렬해서 저장하는 TreeSet을 활용한다.
        Set<Integer> lotto = new TreeSet<>();

        // set에 6개의 데이터가 저장되지 않았다면 6개가 저장될 때까지 반복한다.
        while (lotto.size() < 6) {
            System.out.print("로또 번호 입력: ");
            int num = sc.nextInt();
            if(num < 1 || num > 45) {
                System.out.println("1부터 45 사이의 로또 번호만 입력 가능합니다. 다시 입력하세요.");
                continue;
            }
            // 로또 번호 중복 검사하기
            // 삽입 성공하면 true, 실패하면 false를 리턴한다.
            boolean dup = lotto.add(num);
            if(dup == false) {
                System.out.println("이미 입력한 로또번호입니다. 다시 입력하세요.");
            }
        }

        // TreeSet의 데이터 출력
        System.out.println(lotto);

        // 스캐너 정리하기
        sc.close();
    }
}
