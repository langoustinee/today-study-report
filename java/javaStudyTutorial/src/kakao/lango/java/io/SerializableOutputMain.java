package kakao.lango.java.io;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SerializableOutputMain {
    public static void main(String[] args) {
        // 인스턴스 단위로 기록할 수 있는 스트림 생성하기
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("sample3.dat"))) {
            // 기록할 인스턴스 생성하기
            Data data = new Data(1, "lango", "랑고스틴");
            oos.writeObject(data);
            oos.flush();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
