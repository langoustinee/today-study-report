package kakao.lango.inheritance;

public class Sub extends Super {
    private String nickname;
    private String content;

    // 상위 클래스의 생성자를 호출하여 에러 해결
    public Sub() {
        // 상위 클래스 생성자 호출 전에 작업을 할 경우
        // Call to 'super()' must be first statement in constructor body 문제 발생
//        System.out.println("super class constructor call !!!");
        super(1, "제목입니다.");
        System.out.println("super class constructor call !!!");
    }

    public String getNickname() {
        // private인 postNo에는 접근할 수 없고 protected인 title에만 접근 가능하다.
//        this.title = "제목입니다.";
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        // 상위 클래스의 toString을 호출하려면 하위클래스에도 toString이 존재하기에
        // super.toString() 형식으로 사용한다.
        return super.toString() + " Sub{" +
                "nickname='" + nickname + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
