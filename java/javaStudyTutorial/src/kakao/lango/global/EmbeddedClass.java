package kakao.lango.global;

// 포함되는 클래스
public class EmbeddedClass {
    public int point;
    // 포함하는 클래스의 인스턴스 참조를 기억하기 위한 속성
    private Embed embed;

    // 생성자를 이용한 주입방식
    // 인스턴스를 생성할 때 호출된다.
    // 속도 측면에서는 빠르지만 메모리 효율은 떨어질 수 있다.
    public EmbeddedClass(Embed embed) {
        this.embed = embed;
    }
    
    // setter 메서드를 이용한 주입방식
    // 필요할 때 호출할 수 있다.
    // 속도는 느리지만, 메모리 효율은 뛰어나다.
    public void setEmbed(Embed embed) {
        this.embed = embed;
    }

    public void set() {
        // 포함하는 클래스의 멤버를 수정
        embed.username = "langoustine";
        System.out.println(embed.username);
    }
}
