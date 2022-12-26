package kakao.lango.network;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDownload {
    public static void main(String[] args) {
        // 스레드 실행하기
        new Thread() {
            @Override
            public void run() {
                try {
                    // 다운로드 받을 URL을 생성하기
                    String addr = "https://file.mk.co.kr/meet/neds/2020/11/image_readtop_2020_1143860_16047313154422288.jpg";

                    // 확장자 추출하기
                    // .은 \와 함꼐 기재해야 한다.
                    String[] arr = addr.split("\\.");
                    String ext = arr[arr.length - 1];
                    System.out.println(ext);

                    // URL 생성 및 연결 옵션 설정하기
                    URL url = new URL(addr);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(30000);
                    conn.setUseCaches(false);

                    // 읽어올 바이트 스트림 생성하기
                    InputStream in = conn.getInputStream();

                    // 파일 중복여부 검사후 다운로드하기
                    File f = new File("Jannabi." + ext);
                    if (f.exists()) {
                        System.out.println("이미 존재하는 파일입니다.");
                        return;
                    }
                    // 읽은 내용을 저장할 파일 스트림 생성하기
                    FileOutputStream fos = new FileOutputStream("Jannabi." + ext);

                    while (true) {
                        // 데이터를 저장할 바이트배열 생성하기
                        byte[] raster = new byte[5112];
                        // 바이트 배열에 읽이서 저장하기
                        int len = in.read(raster);
                        // 읽은 데이터가 없다면 종료하기
                        if (len <= 0) {
                            break;
                        }
                        // 읽은 내용을 파일에 기록하기
                        fos.write(raster, 0, len);
                    }
                    // 자원 반납
                    in.close();
                    fos.close();
                    conn.disconnect();
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
        }.start();
    }
}
