package kakao.lango.java.io;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class BufferStreamOutputMain {
    public static void main(String[] args) {
        // 버퍼 단위로 기록하기
        try (PrintStream ps = new PrintStream(new FileOutputStream("./sample2.txt", true))) {
            String msg = "이 이야기는 러시아로부터 전해져 내려왔습니다. 이 메시지를 보는 즉시 주위 7명에게 보내야 합니다." + "\n";
            // wirte는 바이트 단위로 기록하는 것이다.
            ps.write(msg.getBytes());
            // print는 문자열을 스스로 바이트로 변환하여 기록하는 것이다.
            ps.print(msg);
            ps.flush();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
