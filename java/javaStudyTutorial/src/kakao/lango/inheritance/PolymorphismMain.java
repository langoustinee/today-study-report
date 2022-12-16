package kakao.lango.inheritance;

public class PolymorphismMain {
    public static void main(String[] args) {
        // 변수를 선언할 때 사용한 클래스와 인스턴스를 생성하기 위해 호출한 클래스가 동일하다.
        SuperClass superClass = new SuperClass();

        // superClass 변수는 SuperClass의 것들만 호출할 수 있다.
        superClass.superMethod();
        superClass.display();

        // 변수를 선언할 때 사용한 클래스와 인스턴스를 생생허가 위해 호출하는 클래스가 상속관계이기에 가능하다.
        // 인스턴스를 생성하기 위해 호출하는 클래스가 하위클래스여야 한다.
        SuperClass subClass = new SubClass();

        // 위의 subClass는 선언할 때는 SuperClass인데 인스턴스는 SubClass로 생성하였다.
        // subClass가 호출할 수 있는 것은 SuperClass를 참조하지만 호출되는 것은 SubClass의 것이다.
        subClass.superMethod(); // 오버라이딩이 되지 않은 메서드 - SuperClass의 메서드를 호출
        subClass.display(); // 오버라이딩된 메서드 - SubClass의 메서드를 호출

        // 대입된 인스턴스가 SuperClass의 인스턴스이다.
        SuperClass obj = new SuperClass();
        obj.display(); // SuperClass의 display가 호출됨.
        // 대입된 인스턴스가 SubClass의 인스턴스이다.
        obj = new SubClass();
        obj.display(); // SubClass의 display 호출됨.



    }
}
