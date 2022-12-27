package kakao.lango;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;

public class Test2 {

    @Test
    public void testMethod() {
        System.out.println(new TestSource().add(100, 200));
    }

    // 일반적인 테스트 메서드 작성 방식
    @Test
    public void testMethod2() {
        TestSource test = new TestSource();
        // 메서드의 수행 결과 찾아오기
        int res = test.add(100, 250);
        // 예측했던 기대값과 비교하기
        Assert.assertEquals(res, 350);
    }
}
