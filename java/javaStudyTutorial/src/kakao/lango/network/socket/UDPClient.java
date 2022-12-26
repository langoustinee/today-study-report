package kakao.lango.network.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        // trt-with-resources 문법 적용 불가
        // 데이터를 만들 떄 상대방의 주소가 있어야 하는데, 주소 때문에 자원을 try문에서 반납할 수 없다.
        try {
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.print("보낼 메시지: ");
                String msg = sc.nextLine();
                // 보낼 곳의 IP 정보 생성하기
                InetAddress addr = InetAddress.getByName("192.168.0.181");
                // 소켓 생성하기
                DatagramSocket ds = new DatagramSocket();
                // 보낼 패킷 생성하기
                DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.getBytes().length, addr, 8888);
                // 데이터 전송 - 메시지 보내기
                ds.send(dp);

                // 자원 반납하기
                ds.close();
            }

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
