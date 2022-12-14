package kakao.lango.operator;

public class RelationOperator {
    public static void main(String[] args) {
        // 정수끼리는 자료형이 달라도 크리 기뵤 가능
        System.out.println(10 > 3);
        System.out.println(10 > 10L);
        System.out.println(10L > 10);

        // 실수는 크기 비교시 정확한 결과를 만들어내지 못할 수 있다.
        System.out.println((1.0-0.8) >= 0.2);

        int a = 10;
        int b = 10;
        long c = 10L;
        System.out.println((a == b) + " " + (b == c));

        // 실수나 문자열은 주의
        // 문자열의 경우는 리터럴으로 만들었으나 외부에서 입력을 받느냐에 따라 다른 결과가 만들어질 수 있음.

        // 리터럴을 이용해서 선언하면 데이터가 같으므로 동일한 해시코드를 같게됨.
        String s1 = "Java";
        String s2 = "Java";
        // 해시코드가 같으니 true를 출력한다.
        System.out.println((s1 == s2) + " " + s1.equals(s2));
        System.out.println(System.identityHashCode(s1));
        System.out.println(System.identityHashCode(s2));
        // 문자열을 입력받아서 생성 - 리터럴로 선언한 것이 아님
        java.util.Scanner sc = new java.util.Scanner(System.in);
        String s3 = sc.nextLine();
        System.out.println(s3);
        // 동일한 JAVA를 입력해도 해시코드가 다르다.
        System.out.println(System.identityHashCode(s3));
        // 해시코드가 다르기에 false
        System.out.println(s1 == s3);
        // 인스턴스의 경우는 equals로 내용 비교
        System.out.println(s1.equals(s3));

        // new 연산자로 만들게 되면 Heap영역에 존재하기에 리터럴 방식으로 선언한 것과 해시코드가 다르다.
        String s4 = new String("Java");
        System.out.println(s4);
        System.out.println(System.identityHashCode(s4));
        System.out.println(s1 == s4);
        System.out.println(s4.equals(s1));

    }
}
