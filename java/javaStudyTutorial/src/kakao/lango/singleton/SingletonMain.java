package kakao.lango.singleton;

public class SingletonMain {
    public static void main(String[] args) {
//        FirstSingleton singleton1 = new FirstSingleton();
//        SecondSingleton singleton2 = new FirstSingleton();;

        // 두 클래스의 해시코드 확인해보면 다른 인스턴스를 가지기에 해시코드가 다르다.
//        System.out.println(System.identityHashCode(singleton1));
//        System.out.println(System.identityHashCode(singleton2));

        FirstSingleton singleton1 = FirstSingleton.sharedInstance();
        FirstSingleton singleton2 = FirstSingleton.sharedInstance();

        // 두 클래스의 해시코드를 확인해보면 싱글톤 패턴으로 디자인했기에
        // 같은 인스턴스의 참조를 가지고 있어 해시코드가 같음을 알 수 있다.
        System.out.println(System.identityHashCode(singleton1));
        System.out.println(System.identityHashCode(singleton2));


    }
}

