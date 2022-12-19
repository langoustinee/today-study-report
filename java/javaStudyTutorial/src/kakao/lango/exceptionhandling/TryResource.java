package kakao.lango.exceptionhandling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TryResource {
    public static void main(String[] args) {
        String msg = null;
        
        // try ~ resource 구문 적용하기
        // br은 처리가 끝나면 자동으로 close()를 수행한다.
        // jdk 1.7버전에서 추가된 문법이다.
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {
            msg = br.readLine();
            // 여기서 br.close() 구문을 둔다면 정상적으로 try 구문을 실행했을 때라면 상관없다.
            // 하지만 예외가 발생할 경우 catch구문으로 넘어가기에 br.close를 정상적으로 실행할 수가 없다.
            //br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(msg);
    }
}
