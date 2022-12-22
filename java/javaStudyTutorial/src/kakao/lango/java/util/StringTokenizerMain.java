package kakao.lango.java.util;

import java.util.Scanner;
import java.util.StringTokenizer;

public class StringTokenizerMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StringTokenizer st = new StringTokenizer(sc.nextLine());
        String first = st.nextToken();
        String second = st.nextToken();

        System.out.println(first + " " + second);
    }
}
