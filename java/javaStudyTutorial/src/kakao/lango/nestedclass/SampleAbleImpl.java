package kakao.lango.nestedclass;

public class SampleAbleImpl implements SampleAble {
    // 인터페이스의 추상메서드를 반드시 재정의해야 한다.
    @Override
    public void join() {
        System.out.println("인터페이스의 추상메서드를 재정의하였습니다.");
    }

}
