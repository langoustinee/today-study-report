import java.util.List;

import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import di.domain.Good;
import di.persistence.HibernateRepository;

public class HibernateMain {

	public static void main(String[] args) {
		try(GenericApplicationContext context = new GenericXmlApplicationContext("RootConfiguration.xml")) {
			HibernateRepository repository = context.getBean(HibernateRepository.class);
//			Good good = Good.builder()
//					.code(5)
//					.name("악세사리")
//					.manufacture("보세")
//					.price(5900)
//					.build();
			
			/* 데이터 삽입하기 */
//			repository.insertGood(good);
			
			/* 데이터 수정하기 */
//			repository.updateGood(good);
			
//			repository.deleteGood(good);
			
			/* 전체 데이터 가져오기 */
			List<Good> list = repository.allGood();
			for(Good good : list) System.out.println(good);
			
			/* 기본 키를 가지고 하나의 데이터 가져오기 */
			Good good = repository.getGood(2);
			System.out.println("good: " + good);
			
			
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
}
