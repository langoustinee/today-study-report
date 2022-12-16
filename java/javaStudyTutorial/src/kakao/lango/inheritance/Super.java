package kakao.lango.inheritance;

public class Super {
    // private은 상속은 되지만 하위클래스에 접근할 수 없다.
    private int postNo;
    protected String title;

    // Default Constructor를 만들어 하위클래스 에러 해결
    public Super() {

    }

    public Super(int postNo, String title) {
        this.postNo = postNo;
        this.title = title;
    }

    public int getPostNo() {
        return postNo;
    }

    public void setPostNo(int postNo) {
        this.postNo = postNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Super{" +
                "postNo=" + postNo +
                ", title='" + title + '\'' +
                '}';
    }
}
