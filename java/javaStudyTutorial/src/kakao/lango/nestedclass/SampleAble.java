package kakao.lango.nestedclass;

// 인터페이스 안에 속성을 만들 경우 자동으로 final 속성이 된다.
// 인터페이스 안에 메서드를 만들면 전부 추상메서드(abstract method)가 된다.
// 인터페이스 안에 메서드를 만들 때 내용을 추가하고자 하면 default라는 키워드를 추가해야 한다.
// 인터페이스는 인스턴스 생성을 할 수 없지만 변수를 생성하는 것은 가능하다.
// 구현할 클래스에 implements하여 사용한다.
public interface SampleAble {
    // 메서드를 작성하면 자동으로 추상메서드가 된다.
    // 하위클래스에서 반드시 재정의해야 한다.
    public void join();



}
