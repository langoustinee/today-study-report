package kakao.lango.oop;

import java.sql.Date;

public class ConstructorMain {
    public static void main(String[] args) {
        String[] nicknames = {"joon", "hoi"};

        // 인스턴스 생성하고 속성 초기화하기
        Member member = new Member();
        member.setEmail("lango@kakao.com");
        member.setPassword("lango1234");
        member.setNicknames(nicknames);
        member.setBirthday(new Date(95, 9, 5)); // 기본으로 1900을 더해줌.
        member.setMarried(false);
        member.setAge(28);
        System.out.println(member);


        // 6개의 setter없이 init 초기화 메서드 한번만으로 속성 초기화
        // 메서드 호출 횟수가 줄어들어 메모리를 더 효율적으로 사용할 수 있다.
        // 6개의 메서드 호출할 때는 대략 7MB 정도의 메모리를 사용했으나 init 메서드를 통해서는 2MB 정도만 사용하게 된다.
        // 또한 7번보다는 2번만에 돌아올 수 있으므로 더 빠른 수행속도를 보여줄 수 있다.
        Member member1 = new Member();
        member1.init("jooney@kakao.com", "jooney95", nicknames, new Date(95,6,5), false, 28);
        System.out.println(member1);

        // 생성자를 이용한 초기화 - 메서드를 1번만 호출하여 속성을 초기화할 수 있다.
        Member member2 = new Member("hoihoi@kakao.com", "hoihoi5566", nicknames, new Date(95,3,18), false, 28);
        System.out.println(member2);


    }
}
