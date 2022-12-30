package kakao.lango.studylocallogin.service;

import kakao.lango.studylocallogin.dto.UserDTO;

public interface UserService {
    // 로그인 처리를 위한 메서드
    public UserDTO login(String userid, String password, String uuid);

    // 자동 로그인 처리를 위한 메서드
    public UserDTO login(String uuid);

}
