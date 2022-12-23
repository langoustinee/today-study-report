package kakao.lango.java.io;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class SerializableInputMain {
    public static void main(String[] args) {
        // 인스턴스 단위로 읽어올 수 있는 스트림 생성하기
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./sample3.dat"))) {
            // 기록할 인스턴스 생성하기
            // return type이 Object 이므로 기록할 때 사용한 데이터 타입으로 강제 형 변환 하기
            Data data = (Data) ois.readObject();
            System.out.println(data);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
