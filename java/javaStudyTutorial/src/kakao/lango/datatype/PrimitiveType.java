package kakao.lango.datatype;

public class PrimitiveType {
    public static void main(String[] args) {
        // 정수를 저장하고 출력하기
        int a = 10;
        System.out.println(a);

        // 동일한 이름의 변수를 2번 생성시 에러
        //int a =  100;

        // 가리키는 참조(hashcode)값은 같다.
        // 변수에는 데이터의 참조를 저장하는 것이다.
        int b = 10;
        System.out.println("a:" + System.identityHashCode(a));
        System.out.println("b:" + System.identityHashCode(b));

        // 정수는 자신의 자료형보다 큰 데이터를 참조할 수 없다.
//        a = 20000000000;

        // 21억보다 크거나 -21억보다 작은 정수는 long형으로 생성해야 한다.
        long x = 200_000_000_000L;
        System.out.println(x);

        // 16진수 저장
        x = 0x789;
        System.out.println(x);

        // 8진수 저장 - 권한 설정 할 경우
        // rwx로 권한을 표현하는 경우가 대부분임(읽기-쓰기-실행)
        x = 0777;
        System.out.println(x);

        // float형은 정밀도가 소수점 7자리까지이기에 8번째 자리에서 반올림이 된다.
        float f = 0.12345678901234f;
        System.out.println(f);

        char ch = 'A';
        System.out.println(ch);
        System.out.println(ch+1);

        ch = '\u0061';
        System.out.println(ch);

        // int는 21억 정도까지 저장이 가능하지만 연산의 결과는 21억을 넘기 때문에 Overflow 발생
        // 결과값은 음수가 된다.
        x = 2000000000 + 1000000000;
        System.out.println(x);




    }
}
