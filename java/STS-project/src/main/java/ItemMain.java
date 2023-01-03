import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import domain.Item;
import persistence.ItemRepository;
import persistence.RepositoryFactory;

public class ItemMain {
	public static void main(String[] args) {
//		ItemRepository itemRepository = new ItemRepository();
		
		// 인스턴스 생성할 때 다른 팩토리 클래스를 이용하여 생성하는 팩토리 메서드 패턴을 적용한다.
		// 팩토리 메서드 패턴은 다른 클래스의 메서드를 이용하여 인스턴스를 생성하는 방식이다.
//		ItemRepository itemRepository = RepositoryFactory.create();
		
		// 스프링이 인스턴스를 생성하도록 하기
//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RepositoryFactory.class);	
		/*
		 * // RepositoryFactory 클래스의 create 메서드를 호출하여 ItemRepository 클래스의 인스턴스를 생성한다.
		 * ItemRepository itemRepository = context.getBean("create",
		 * ItemRepository.class); ItemRepository itemRepository1 =
		 * context.getBean("create", ItemRepository.class);
		 * 
		 * // Spring은 싱글톤 패턴으로 생성하기 때문에 아래 2개의 인스턴스의 해시코드는 일치한다.
		 * System.out.println(System.identityHashCode(itemRepository));
		 * System.out.println(System.identityHashCode(itemRepository1));
		 * 
		 * Item item = itemRepository.get();
		 * System.out.println(item);
		 * context.close();
		 */
		
		// XML을 이용한 Bean 생성하기
		try(GenericApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml")) {
			// bean 태그의 id와 클래스이름을 기재한다.
			/*
			 * ItemRepository itemRepository = context.getBean("itemRepository",
			 * ItemRepository.class); Item item = itemRepository.get();
			 * System.out.println(item);
			 */
			
			Item item = context.getBean("item", Item.class);
			System.out.println(item);
		
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		
		
		
		
	}
}
