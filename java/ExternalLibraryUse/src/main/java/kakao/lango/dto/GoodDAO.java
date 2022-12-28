package kakao.lango.dto;

import java.util.List;

// goods 테이블에 수행할 데이터베이스 작업의 원형을 소유할 인터페이스이다.
public interface GoodDAO {
    // goods 테이블의 전체 데이터를 가져올 추상 메서드 선언
    public List<Good> getAllGoods();

    // goods 테이블에서 code를 가지고 하나의 데이터를 조회할 추상 메서드 선언
    public Good getGood(String code);

    // goods 테이블에서 name이나 menufacture가 포함된 데이터를 가져오는 추상 메서드 선언하기
    public List<Good> getLikeGood(String content);

    // goods 테이블에 하나의 Good 데이터 삽입할 추상 메서드 선언하기
    // 삽입 및 수정 메서드는 모양이 동일하다.
    // 삭제는 동일하게 만들어도, 매개변수를 기본키로 만들어도 무방하다.
    public int addGood(Good good);

}
