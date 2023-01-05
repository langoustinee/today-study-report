import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import di.controller.PostsController;

public class PostsMain {
	public static void main(String[] args) {
		// XML을 이용한 Bean 생성하기
				try(GenericApplicationContext context = new GenericXmlApplicationContext("RootConfiguration.xml")) {
					// Controller 가져오기
					PostsController controller = context.getBean("postsController", PostsController.class);
					controller.findById(1);
				
				} catch (Exception e) {
					System.out.println(e.getLocalizedMessage());
				}
	}
}
