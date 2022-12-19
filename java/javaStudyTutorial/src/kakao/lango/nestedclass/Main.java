package kakao.lango.nestedclass;

public class Main {
    public static void main(String[] args) {
        // 외부 클래스의 인스턴스를 생성하기
        InstanceInner instanceInner = new InstanceInner();

        // 내부 클래스의 인스턴스를 생성하기 - Instance Inner Class
//        InstanceInner.Inner inner = new InstanceInner.Inner();

        // Static Inner Class의 인스턴스 생성하기
        InstanceInner.Inner inner = new InstanceInner.Inner();

        // 인터페이스를 구현한 클래스의 인스턴스 생성하기
        //SampleAbleImpl instance = new SampleAbleImpl();

        // SampleAbleImpl의 참조가 아닌 SampleAble의 참조로 변수를 생성한다.
        // 인터페이스나 추상클래스 등을 상속하여 클래스를 만들고 인스턴스를 생성할 경우
        // 변수는 대부분 인터페이스나 추상클래스명으로 만들고 생성자는 사용하고자 하는 클래스의 생성자를 이용하는 경우가 많다.
        // 상위클래스나 인터페이스로 만들어진 변수에 하위클래스의 인스턴스 참조를 대입할 수 있는데 이렇게 만들어진 변수는 상위클래스나 인터페이스에 존재하는 이름만 호출이 가능하다.
        // 실제 호출되는 내용은 생성자를 따라 간다. 컴파일시에는 변수의 자료형, 실행되는 시점에는 대입된 데이터를 본다.
        SampleAble instance = new SampleAbleImpl();

        // 메서드 호출하기
        instance.join();

        // Anonymous Class 사용하기
        //
        SampleAble anonymous = new SampleAble() {
            @Override
            public void join() {
                System.out.println("anonymous를 사용하였습니다.");
            }
        };
        // anoymous를 이용하여 메서드를 호출하기
        // SampleAble의 인스턴스를 만들지 않고도 해당 메서드를 호출할 수 있다.
        anonymous.join();

        // 메서드를 1번만 호출한다면 변수를 생성하지 않고도 사용할 수 있다.
        new SampleAble() {
            @Override
            public void join() {
                System.out.println("변수 선언없이 anonymous를 사용하였습니다.");
            }
        }.join();
    }
}
