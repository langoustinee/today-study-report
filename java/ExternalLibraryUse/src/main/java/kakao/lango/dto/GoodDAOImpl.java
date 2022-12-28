package kakao.lango.dto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// GoodDAO를 구현하는 클래스이다.
public class GoodDAOImpl implements GoodDAO {
    // 싱글톤 패턴을 적용하기
    // 외부에서 인스턴스 생성을 못하도록 생성자를 private으로 설계
    private GoodDAOImpl() {
    }

    // 참조를 저장할 변수를 static으로 생성
    private static GoodDAO goodDAO;

    // 변수가 null이면 인스턴스를 생성하고 null 아니면 바로 리턴한다.
    public static GoodDAO getInstance() {
        if (goodDAO == null) goodDAO = new GoodDAOImpl();
        return goodDAO;
    }

    // 데이터베이스 연결해 필요한 변수
    private Connection connection;
    // SQL을 실행하는 변수
    private PreparedStatement pstmt;
    // SELECT문의 결과를 담을 변수
    private ResultSet rs;

    // static 초기화 영역
    // 클래스가 로드될 때 최초 한번만 수행한다.
    // static 속성만 사용 가능하다.
    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
//            System.out.println("드라이버 로드 성공!");

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
    // 초기화 영역
    // 생성자를 호출할 때마다 먼저 호출된다.
    // 추후 init이라는 메서드로 만들어지는 영역이다.
    // 모든 속성을 사용할 수 있다.
    {
        // 2. 데이터베이스 접속하기
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/jdbc","guest1", "guest1");
//            System.out.println(connection);
        } catch(Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    // goods 테이블의 전체 데이터 가져올 메서드 구현하기
    @Override
    public List<Good> getAllGoods() {
        // List는 조회할 데이터가 없더라도 인스턴스를 생성한다.
        // 조회할 데이터가 없다는 것은 size()가 0이라는 것이다.
        List<Good> list = new ArrayList<>();
        try {
            // SQL 실행 클래스의 인스턴스를 생성한다.
            pstmt = connection.prepareStatement("select * from goods");
            // 매개변수가 없다면 바로 SQL을 실행한다.
            rs = pstmt.executeQuery();
            // 데이터를 하나의 행씩 읽어와 DTO 객체로 변환하여 list에 대입한다.
            while (rs.next()) {
                Good good = new Good();
                good.setCode(rs.getString("code"));
                good.setName(rs.getString("name"));
                good.setMenufacture(rs.getString("manufacture"));
                good.setPrice(rs.getInt("price"));
                list.add(good);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return list;
    }

    // goods 테이블에서 code를 가지고 하나의 데이터를 조회할 메서드 구현하기
    @Override
    public Good getGood(String code) {
        // null이면 조회할 데이터가 없다는 의미이다.
        Good good = null;
        try {
            pstmt = connection.prepareStatement("select * from goods where code = ?");
            // 데이터 바인딩 - 첫번쨰 ?에 바인딩한다.
            // ?에 바인딩할 때 인덱스는 0이 아니라 1부터 시작한다.
            pstmt.setString(1,code);
            rs = pstmt.executeQuery();
            // 받아온 Good 데이터가 1개밖에 없기에 반복문이 아닌 fi문을 작성한다.
            if (rs.next()) {
                good = new Good();
                good.setCode(rs.getString("code"));
                good.setName(rs.getString("name"));
                good.setMenufacture(rs.getString("manufacture"));
                good.setPrice(rs.getInt("price"));
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return good;
    }

    // goods 테이블에서 name이나 menufacture이 포함된 Good 데이터를 가져오는 메서드 구현하기
    @Override
    public List<Good> getLikeGood(String content) {
        List<Good> list = new ArrayList<>();
        try {
            pstmt = connection.prepareStatement("select * from goods where name like ? or manufacture like ?");
            pstmt.setString(1, "%" + content + "%");
            pstmt.setString(2, "%" + content + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Good good = new Good();
                good.setCode(rs.getString("code"));
                good.setName(rs.getString("name"));
                good.setMenufacture(rs.getString("manufacture"));
                good.setPrice(rs.getInt("price"));
                list.add(good);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return list;
    }

    // 하나의 Good 데이터 삽입할 메서드 구현하기
    @Override
    public int addGood(Good good) {
        int result = 0;
        try {
            // 삽입 작업이기에 트랜잭션을 고려해야한다. - AutoCommit 해제
            connection.setAutoCommit(false);
            pstmt = connection.prepareStatement("insert into goods values (?, ?, ?, ?)");
            pstmt.setString(1, good.getCode());
            pstmt.setString(2, good.getName());
            pstmt.setString(3, good.getMenufacture());
            pstmt.setInt(4, good.getPrice());
            result = pstmt.executeUpdate();

            // 성공하면 commit
            connection.commit();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            try {
                // 실패하면 rollback
                connection.rollback();
            } catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
                e.printStackTrace();
            }
            e.printStackTrace();
        }

        return result;
    }
}