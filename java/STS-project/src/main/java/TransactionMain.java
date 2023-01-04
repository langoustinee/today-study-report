import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import di.persistence.TransactionRepository;

public class TransactionMain {
	public static void main(String[] args) {
		try(GenericApplicationContext context = new GenericXmlApplicationContext("RootConfiguration.xml")) {
			TransactionRepository repository = context.getBean(TransactionRepository.class);
			repository.insert();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
}
