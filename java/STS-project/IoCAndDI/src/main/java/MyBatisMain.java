import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import di.domain.Good;
import di.persistence.GoodMapper;
import di.persistence.GoodRepository;

public class MyBatisMain {

	public static void main(String[] args) {
		try(GenericApplicationContext context = new GenericXmlApplicationContext("RootConfiguration.xml")) {
			
//			GoodRepository repository = context.getBean(GoodRepository.class);
			
			/* insertGood 메서드로 하나의 Good 삽입하기 */
//			Good good = Good.builder()
//					.code(2)
//					.name("상의")
//					.manufacture("컨템포러리")
//					.price(99000)
//					.build();
//			repository.insertGood(good);
			
			/* allGood 메서드로 모든 Good 가져오기 */
//			List<Good> list = repository.allGood();
//			for(Good good : list) {
//				System.out.println("Good:" + good);
//			}
			
			/* getGood 메서드로 하나의 Good 가져오기 */
//			int code = 2;
//			Good good = repository.getGood(code);
//			System.out.println("get Good: " + good);
			
			
			/* 인터페이스로 allGood 메서드 호출하여 모든 Good 가져오기 */
			GoodMapper repository = context.getBean(GoodMapper.class);
			List<Good> list = repository.allGood();
			for(Good good : list) {
				System.out.println("Good:" + good);
			}
			
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

	}

}
