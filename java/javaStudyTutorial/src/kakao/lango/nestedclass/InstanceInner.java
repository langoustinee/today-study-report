package kakao.lango.nestedclass;

public class InstanceInner {
    // 클래스 안의 클래스 생성하기
    // static 멤버가 있을 경우 일반 클래스에서는 사용이 불가하기에 Inner 클래스에 static을 붙여야 한다.
    // 내부 클래스의 특성에 static이 추가되면 외부에서 인스턴스를 만들 때 클래스명.new 내부클래스명()의 형태로 작성한다.
    // 안드로이드에서 뷰의 이벤트를 처리하기 위한 언터페이스들이 이런 방식으로 설계되어 있다.
    public static class Inner {
        public int score;
        // 내부 클래스에 static 속성
        public static int autoIncrement;

    }
}
