package kakao.lango.inheritance;

// Protoss, Zerg, Terran 클래스의 인스턴스를 하나의 변수에 대입할 수 있도록 하기 위한 상위 클래스
// 추상 클래스
//public abstract class Starcraft {
//
//    // 오버라이딩을 위해서 생성한 메서드 - 추상 메서드로 선언
//    // 추상메서드는 추상클래스나 인터페이스에만 존재한다.
//    public abstract void attack();
//}

// 인터페이스로 변경
public interface Starcraft {
    public abstract void attack();
}

