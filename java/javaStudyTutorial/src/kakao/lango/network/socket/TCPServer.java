package kakao.lango.network.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        try {
            // 서버 소켓 생성하기, 항상 포트번호를 부여해야 한다.
            ServerSocket ss = new ServerSocket(9999);
            while (true) {
                System.out.println("서버 대기 중입니다.");
                // 클라이언트가 접속할 때까지 대기하기
                Socket socket = ss.accept();
                // 접속한 클라이언트 정보 확인하기
                System.out.println(socket.toString());
                // 클라이언트가 전송한 내용 읽어오기
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                // 한줄의 메시지를 읽어오기
                String msg = br.readLine();
                System.out.println(msg);

                // 자원 반납하기
                br.close();
                socket.close();
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
