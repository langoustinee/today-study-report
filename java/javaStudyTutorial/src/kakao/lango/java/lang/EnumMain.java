package kakao.lango.java.lang;

// 이런 형태로 만들면 Gender Type에 변수에는 MAN 아니면 WOMAN만 가능하다.
//enum Gender {
//    MAN,
//    WOMAN;
//}
public class EnumMain {
    public static void main(String[] args) {
        // 상수(옵션) 사용을 위해 이전에는 final 상수를 이용했다.
        final int MAN = 0;
        final int WOMAN = 1;

        // int로 만들게 되면 정의하지 않은 값(0과 1이 아닌 정수 ...)이 대입될 수 있다.
        int gender = MAN;
        gender = 3;

        // Gender가 enum 이기 때문에 MAN이나 WOMAN만 저장할 수 있다.
        Gender man1 = Gender.MAN;
        Gender woman1 = Gender.WOMAN;

    }
}
