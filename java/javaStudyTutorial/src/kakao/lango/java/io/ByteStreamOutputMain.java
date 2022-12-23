package kakao.lango.java.io;

import java.io.FileOutputStream;

public class ByteStreamOutputMain {
    public static void main(String[] args) {
        // 파일에 바이트 단위로 기록하는 코드 작성하기

        /*
        1. 아래와 같이 try/catch를 2번이나 사용하기에 가독성이 떨어진다.
        FileOutputStream fos = null;
        
        try {
            fos = new FileOutputStream("./sampleOut.bin");
            fos.write(10);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            try {
                fos.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        */

        // 위 코드를 아래와 같이 수정하여 가독성을 높일 수 있다.
        // appendMode를 true로 하면 덮어쓰기되지 않고 내용이 추가된다.
        try (FileOutputStream fos = new FileOutputStream("./sample.txt",true)) {
            // 파일에 기록할 내용 만들기 - 내용만 바뀐다면 덮어쓰기 되버린다.
            String msg = "\n" + "Hello I/O!";
            fos.write(msg.getBytes());
            fos.flush();
        } catch (Exception e) {
            // 추후에는 해당 내용을 파일에도 기록하여 로깅을 강화하는 것이 좋다.
            System.out.println(e.getLocalizedMessage());
        }


    }
}
