package kakao.lango.dto;

public class GoodMain {
    public static void main(String[] args) {
        GoodDAO dao = GoodDAOImpl.getInstance();

        // 전체 데이터 가져오기
//        System.out.println(dao.getAllGoods());

        // 하나의 Good 데이터 가져오기
//        System.out.println(dao.getGood("1"));
//        System.out.println(dao.getGood("8"));
//        System.out.println(dao.getGood("20"));

        // 하나의 데이터 삽입하기
//        Good good = new Good();
//        good.setCode("10");
//        good.setName("신발");
//        good.setMenufacture("대만");
//        good.setPrice(87000);
//
//        int res = dao.addGood(good);
//        if (res == 1) System.out.println("Good 데이터 삽입 성공!");
//        else System.out.println("Good 데이터 삽입 실패...");
        
        // name이나 menufacture가 포함된 데이터 가져오기
        System.out.println(dao.getLikeGood("한국"));
        System.out.println(dao.getLikeGood("이"));
    }
}

