package kakao.lango.java.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TryCatch {
    public static void main(String[] args) {
        // BufferedReader로 입력받기
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(br.readLine());
            br.close();
        } catch (Exception e) {
            try {
                br.close();
            } catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
            }
        }
    }
}
