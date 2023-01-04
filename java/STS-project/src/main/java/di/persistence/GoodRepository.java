package di.persistence;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import di.domain.Good;

//@Repository
public class GoodRepository {
	
//	@Autowired
	private SqlSession sqlSession;

	// 삽입을 제외한 모든 메서드의 리턴 타입은 int형이다.
	public int insertGood(Good good) {
		return sqlSession.insert("insertGood", good);
	}
	
	/*모든 Good들을 가져오는 메서드*/
	public List<Good> allGood() {	
		return sqlSession.selectList("allGood");
	}
	
	/*하나의 Good을 가져오는 메서드*/
	public Good getGood(int code) {
		return sqlSession.selectOne("getGood", code);
	}
}
