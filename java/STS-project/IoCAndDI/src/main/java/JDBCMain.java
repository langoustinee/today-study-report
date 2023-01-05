import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class JDBCMain {

	public static void main(String[] args) {
		// XML을 이용한 Bean 생성하기
		try(GenericApplicationContext context = new GenericXmlApplicationContext("RootConfiguration.xml")) {
			
			SqlSessionFactory sqlFactory = context.getBean("sqlSessionFactory", SqlSessionFactory.class);
			System.out.println("sqlFactory: " + sqlFactory);
			
			SqlSession session = sqlFactory.openSession();
			System.out.println("session: " + session);
			
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

	}

}
