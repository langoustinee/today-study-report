package kakao.lango.java.lang;

import java.util.Arrays;
import java.util.Objects;

// 하나의 데이터 묶음을 표현하기 위한 클래스이다. Value Object
// 복제하기 위한 clone 메서드 사용을 위해 Cloneable 인터페이스 구현
public class Data implements Cloneable {
    private int postId;
    private String title;
    private String content;
    private String[] images;

    // 기본 생성자 - Default Constructor
    public Data() {

    }

    // 속성을 대입받아서 생성하는 생성자
    // 인스턴스를 빠르게 초기화하기 위해서 사용한다.
    public Data(int postId, String title, String content, String[] images) {
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

    // 인스턴스의 내용을 빠르게 만들기 위해서 사용한다.
    // 디버깅을 위한 메서드로 동작한다.
    @Override
    public String toString() {
        return "Data{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", images=" + Arrays.toString(images) +
                '}';
    }

    public Data clone() {
        Data data = new Data();
        // 아래 방식은 얕은복사를 진행한 것이다.
        // postId는 숫자 데이터이므로 바로 복제할 수 있다.
        // title, content, images는 숫자데이터가 아니기에 바로 대입하면 참조가 대입된다.
        data.setPostId(this.postId);
        data.setTitle(this.title);
        data.setContent(this.content);
        //data.setImages(this.images);

        // 깊은 복사를 해야하기에 배열을 복제한다.
        // 1. 직접 구현하기
//        String[] images = new String[this.images.length];
//        for(int i=0; i<images.length; i++) {
//            images[i] = this.images[i];
//        }
        // 2. Arrays.copy 메서드 이용하기
        String[] images = Arrays.copyOf(this.images, this.images.length);
        data.setImages(images);

        return data;
    }

    // 데이터의 내용을 비교하는 메서드
    @Override
    public boolean equals(Object other) {
        boolean result = true;
        // 원래 자료형으로 변환하기
        Data other1 = (Data) other;
        // 숫자나 boolean 형은 ==로 일치여부를 판단하지만 그외의 자료형은 equals로 판단해야한다.
        if(this.postId == other1.getPostId() && this.title.equals(other1.getTitle())) return true;
        //return result;

        // Objects.hash(데이터 나열)을 이용하면 데이터를 가지고 정수로 만든 해시코드를 생성한다.
        // 이렇게 만든 해시코드 값을 리턴하는 것이 더 속도가 빠르다.
        return (Objects.hash(postId, title, content) == Objects.hash(other1.getPostId(), other1.getTitle(), other1.getContent()));
    }
}
