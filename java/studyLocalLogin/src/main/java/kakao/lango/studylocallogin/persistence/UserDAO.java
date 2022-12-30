package kakao.lango.studylocallogin.persistence;

import kakao.lango.studylocallogin.domain.UserVO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    // 데이터베이스 작업을 위한 기능들은 모두 싱글톤 패턴으로 이루어져야 한다.
    // Spring에서는 이 작업이 필요 없다.
    private UserDAO() { }

    private static UserDAO dao;

    public static UserDAO getInstance() {
        if (dao == null) {
            dao = new UserDAO();
        }
        return dao;
    }

    // 데이터베이스 접속을 위한 드라이버 로드하기
    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Driver Load Success!");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    // 데이터베이스 사용을 위한 속성 정의하기
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    // 데이터베이스 연결하기
    {
        try {
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/lango", "guest1", "guest1");
            System.out.println("DB connection success!");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    // 로그인 처리를 위한 메서드
    // id와 password를 받아서 처리한 후 회원정보를 리턴한다.
    public UserVO login(String userid, String password) {
        UserVO vo = null;
        try {
            // 수행할 sql문 작성하기
            String sql = "select * from users where userid=? and password=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userid);
            pstmt.setString(2, password);

            // sql 실행하기
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // 비밀번호는 세션에 저장할 필요가 없기 떄문에 가져오지 않는다.
                vo = new UserVO();
                vo.setUserid(rs.getString("userid"));
                vo.setNickname(rs.getString("nickname"));
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return vo;
    }

    // uuid를 가지고 로그인하는 메서드
    public UserVO login(String uuid) {
        UserVO vo = null;
        try {
            String sql = "select * from users where uuid=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, uuid);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                vo = new UserVO();
                vo.setUserid(rs.getString("userid"));
                vo.setNickname(rs.getString("nickname"));
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return vo;
    }

    // uuid를 업데이트하는 메서드
    public void updateUUID(String userid, String uuid) {
        try {
            // uuid를 변경하는 sql문 실행하기
            String sql = "update users set uuid = ? where userid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, uuid);
            pstmt.setString(2, userid);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

}
