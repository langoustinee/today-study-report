package kakao.lango.network.socket;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {
        try {
            // 접속할 컴퓨터의 IP 가져오기
            InetAddress addr = InetAddress.getByName("192.168.0.181");
            Scanner sc = new Scanner(System.in);

            // 무한 반복하여 문자열 서버에 전송하기
            while (true) {
                // 9999번 포트로 연결하기
                Socket socket = new Socket(addr, 9999);
                // 문자열 전송을 위한 스트림 만들기
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                System.out.print("메시지: ");
                String msg = sc.nextLine();
                pw.println(msg);
                pw.flush();

                // 자원 반납하기
                pw.close();
                socket.close();
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
