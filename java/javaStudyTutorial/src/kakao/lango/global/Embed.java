package kakao.lango.global;

public class Embed {
    public String username;

    public void createEmbedded() {
        // 다른 클래스의 인스턴스를 생성한다.
        // 생성자를 이용한 주입방식 - 생성자를 호출할 때 현재 인스턴스의 참조를 넘겨주는 것이다.
        EmbeddedClass obj = new EmbeddedClass(this);

        // setter메서드를 이용한 주입방식
        obj.setEmbed(this);

        // 포함하는 인스턴스가 포함되는 인스턴스의 멤버 호출하기
        obj.point = 3;
        System.out.println(obj.point);

    }
}
