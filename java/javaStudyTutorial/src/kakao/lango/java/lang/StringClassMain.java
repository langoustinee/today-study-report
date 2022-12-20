package kakao.lango.java.lang;

public class StringClassMain {
    public static void main(String[] args) {
        String str = "Hello World!";
        System.out.println(System.identityHashCode(str));

        // String은 데이터 수정이 불가하기에 새로운 공간에 기존 문자열을 복사한 후 작업을 수행하고 참조를 리턴한다.
        // 그래서 기존의 데이터가 저장된 공간은 메모리 낭비가 될 수 있다.
        str += " Java!";
        System.out.println(System.identityHashCode(str));

        // StringBuilder로 변경 가능한 문자열을 저장하기
        StringBuilder sb = new StringBuilder();
        System.out.println(System.identityHashCode(sb));
        // 문자열을 추가하면 현재 저장된 공간에 이어붙이기를 수행한다.
        sb.append(" Java!");
        // 해시코드가 이전과 같음을 확인할 수 있다.
        System.out.println(System.identityHashCode(sb));



    }
}
