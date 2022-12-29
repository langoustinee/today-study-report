package kakao.lango.studyjavaweb;

// 사용자의 요청을 처리할 메서드의 원형을 가진 인터페이스이다.
public interface PageService {
    // 2개의 정수를 받아서 합계를 구한 후 리턴하는 메서드이다.
    public int getSum(int a, int b);

}
