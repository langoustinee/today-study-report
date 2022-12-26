package kakao.lango.network.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) {
        // try-with-resources 문법 적용하기
        // UDP의 경우 연결하는 소켓을 만들 필요가 없다.
        try (DatagramSocket dsoc = new DatagramSocket(8888)) {
//            byte[] data = new byte[65536];
//            // 데이터를 전송받을 패킷 클래스의 인스턴스 생성하기
//            DatagramPacket dp = new DatagramPacket(data, data.length);
            while (true) {
                byte[] data = new byte[65536];
                // 데이터를 전송받을 패킷 클래스의 인스턴스 생성하기
                DatagramPacket dp = new DatagramPacket(data, data.length);
                // 데이터를 받아오기
                dsoc.receive(dp);
                // 클라이언트 정보 확인하기
                System.out.println(dp.getAddress());
                // 클라이언트에게 받은 메시지 출력하기
                System.out.println(new String(dp.getData()));
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
