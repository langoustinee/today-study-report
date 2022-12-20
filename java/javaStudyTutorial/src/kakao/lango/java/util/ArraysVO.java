package kakao.lango.java.util;
import java.util.Arrays;

// 크기비교가 가능한 메서드가 있다는 것을 보장한다.
public class ArraysVO implements Comparable<ArraysVO> {
    private int postId;
    private String title;
    private String content;
    private String[] images;

    public ArraysVO() {
        super();
    }

    public ArraysVO(int postId, String title, String content, String[] images) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.images = images;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "ArraysVO{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", images=" + Arrays.toString(images) +
                '}';
    }

    // 크기를 비교하는 메서드
    // 양수를 리턴하면 순서가 변경되고 음수를 리턴하면 순서가 변경되지 않는다.
    @Override
    public int compareTo(ArraysVO o) {
        // 숫자는 뺄셈을 통해 리턴하면 된다.
        
        // 숫자 데이터 기준으로 오름차순 정렬
        //return this.postId - o.postId;
        // 숫자 데이터 기준으로 내림차순 정렬
//        return o.postId - this.postId;

        // 문자열은 뺄셈이 되지 않는다.
        // 문자열은 Comparable 인터페이스를 implements 되어 있어 comrareTo 메서드로 비교하면 된다.
        // 문자열 데이터를 기준으로 내림차순 정렬
        return o.title.compareTo(this.title);
    }
}