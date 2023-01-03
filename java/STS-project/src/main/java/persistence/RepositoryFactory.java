package persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 인스턴스를 생성해주는 Factory 클래스이다.
// @Configuration는 팩토리 클래스라는 어노테이션이다.
@Configuration
public class RepositoryFactory {
	// create 대신에 newInstance를 사용해도 같은 의미로 통용된다.
	// 매번 인스턴스를 생성해서 제공하는 메서드이다.
	// @Bean은 인스턴스를 만들어주는 메서드이다.
	@Bean
	public static ItemRepository create() {
		return new ItemRepository();
	}
}
