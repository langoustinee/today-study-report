import kakao.lango.studylocallogin.persistence.UserDAO;
import kakao.lango.studylocallogin.service.UserService;
import kakao.lango.studylocallogin.service.UserServiceImpl;
import org.junit.jupiter.api.Test;

public class DBConnectionTest {
    @Test
    public void daoTest() {
        UserDAO dao = UserDAO.getInstance();
        System.out.println(dao);
        System.out.println(dao.login("lango","1234")); // null
        System.out.println(dao.login("lango","kakao123")); // success
        System.out.println(dao.login("kakao","1234")); // null

    }

    @Test
    public void serverTest() {
        UserService service = UserServiceImpl.getInstance();
//        System.out.println(service.login("lango", "kakao123"));
//        System.out.println(service.login("lango", "aaaa"));
//        System.out.println(service.login("123123", "kakao123"));
    }

}
