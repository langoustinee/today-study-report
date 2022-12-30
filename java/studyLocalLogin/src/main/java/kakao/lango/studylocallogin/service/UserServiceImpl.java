package kakao.lango.studylocallogin.service;

import kakao.lango.studylocallogin.domain.UserVO;
import kakao.lango.studylocallogin.dto.UserDTO;
import kakao.lango.studylocallogin.persistence.UserDAO;

public class UserServiceImpl implements UserService {

    // 서비스에는 항상 DAO가 주입되어야 한다.
    private UserDAO userDAO;

    private UserServiceImpl() {
        userDAO = UserDAO.getInstance();
    }

    private static UserService service;

    public static UserService getInstance() {
        if (service == null) {
            service = new UserServiceImpl();
        }
        return service;
    }

    @Override
    public UserDTO login(String userid, String password, String uuid) {
        // 필요한 DAO의 메서드를 호출하여 Controller에게 응답을 전송한다.
        UserDTO dto = null;
        UserVO vo = userDAO.login(userid, password);

        // VO를 DTO로 변환하기
        if (vo != null) {
            dto = new UserDTO();
            dto.setUserid(vo.getUserid());
            dto.setNickname(vo.getNickname());
            // 로그인 성공시 uuid 업데이트하기
            userDAO.updateUUID(userid, uuid);
        }
        return dto;
    }

    // uuid를 가지고 로그인하는 메서드
    @Override
    public UserDTO login(String uuid) {
        UserDTO dto = null;

        UserVO vo = userDAO.login(uuid);
        if (vo != null) {
            dto = new UserDTO();
            dto.setUserid(vo.getUserid());
            dto.setNickname(vo.getNickname());
        }

        return dto;
    }
}
