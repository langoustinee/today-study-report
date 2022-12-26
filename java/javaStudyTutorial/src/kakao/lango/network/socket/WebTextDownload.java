package kakao.lango.network.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class WebTextDownload {
    public static void main(String[] args) {
        // try-with-resources 문법 적용 불가
        try {
            // www.kakao.com 주소 정보 가져오기
            InetAddress addr = InetAddress.getByName("www.kakao.com");
            // 카카오에 연결할 소켓 생성하기
            Socket socket = new Socket(addr, 80);
            // 요청을 전송할 수 있는 CharacterStream(스트림) 생성하기
            PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

            // 요청 전송하기
            ps.println("GET https://www.kakao.com");
            ps.flush();

            //  문자 단위로 전송을 받기 위한 스트림 생성하기
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 여러 줄의 문자열을 하나로 만들기
            StringBuilder sb = new StringBuilder();
            while (true) {
                String imsi = br.readLine();
                if (imsi == null) {
                    break;
                }
                sb.append(imsi + "\n");
            }
            String html = sb.toString();
            System.out.println(html);
            
            // 사용한 자원 반납하기
            br.close();
            ps.close();
            socket.close();

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
