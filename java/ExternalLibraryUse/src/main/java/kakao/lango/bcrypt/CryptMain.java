package kakao.lango.bcrypt;

import org.mindrot.jbcrypt.BCrypt;

public class CryptMain {
    public static void main(String[] args) {
        // 복호화가 불가능한 암호화 하기
        // 64자리나 128자리
        String encryptString = BCrypt.hashpw("123123123", BCrypt.gensalt());
        System.out.println(encryptString);
        // $2a$10$VRQGn32BMI8KoZl1uGuv6OMIcX0hD/m93/BiL9PWefFhZh3gM7Nhi

        // 비교하기
        // 첫번쨰로 평문을 전달하고 두번째로 암호화 문장을 전달해야 한다.
        // Error
//        System.out.println(BCrypt.checkpw(encryptString, "123123123"));
        System.out.println(BCrypt.checkpw("123123123", encryptString));
    }
}
