package kakao.lango.singleton;

public class FirstSingleton {
    // 외부에서 직접 인스턴스 생성을 못하도록 생성자의 접근지정자를 privatea로 변경한다.
    private FirstSingleton() { }

    // 하나의 인스턴스 참조를 저장하기 위한 속성을 생성한다.
    private static FirstSingleton singleton;

    // 인스턴스의 참조를 리턴하는 메서드를 생성한다.
    public static FirstSingleton sharedInstance() {
        if(singleton == null) singleton = new FirstSingleton();
        return singleton;
    }
}
