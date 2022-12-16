package kakao.lango.inheritance;

public class InheritanceMain {
    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.setPostNo(1);
        sub.setTitle("하위 클래스에서 상위클래스의 속성을 상속받아 사용합니다.");
        sub.setNickname("lango");
        sub.setContent("Sub는 하위 클래스입니다.");
        System.out.println(sub);

        Super sp1 = new Super();
        Sub sb1 = new Sub();
        // 상위 클래스 타입의 참조형 변수에는 하위 클래스 타입의 인스턴스 참조를 바로 대입할 수 있다.
        Super sp2 = new Sub();
        // 반대로 하위클래스 타입의 참조형 변수에는 상위클래스 타입의 인스턴스 참조를 바로 대입할 수 없다.
//        Sub sb2 = new Super();

        // 하지만 강제 형 변환을 이용해 할 수는 있다.
        // sp2에 대입된 인스턴스는 원래 Sub 타입이기에 문제가 없다.
        Sub sb3 = (Sub) sp2;
        // 컴파일 단계에서는 문법적으로 오류는 없으나 실제 호출될 때 예외가 발생함
        // sb4에 대입하는 인스턴스는 Super 타입이기에 예외가 발생한다.
        Sub sb4 = (Sub) new Super();



    }
}
