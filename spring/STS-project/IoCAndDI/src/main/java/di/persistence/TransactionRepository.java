package di.persistence;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TransactionRepository {
	
	@Autowired
	private SimpleJdbcInsert template;
	
	// @Transactional으로 인해 중간에 예외 발생시 모든 작업이 rollback 된다.
	// 대부분의 경우는 Service에서 트랙잭션 처리를 한다.
	@Transactional
	public void insert() {
		template.withTableName("goods");
		Map<String, Object> hm = new HashMap<String, Object>();
		
		hm.put("code", 3);
		hm.put("name", "하의");
		hm.put("manufacture", "도메스틱");
		hm.put("price", 159000);
		
		template.execute(hm);
		// 두번 실행하게 되면 code(기본 키) 중복으로 예외가 발생한다.
//		template.execute(hm); 
	}
}
