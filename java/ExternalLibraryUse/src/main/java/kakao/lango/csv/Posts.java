package kakao.lango.csv;

import java.util.Date;

public class Posts {
    private int postId;
    private String title;
    private String content;
    private String img;
    private int like;
    private Date deadLine;

    public Posts() {
        super();
    }

    public Posts(int postId, String title, String content, String img, int like, Date deadLine) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.img = img;
        this.like = like;
        this.deadLine = deadLine;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", img='" + img + '\'' +
                ", like=" + like +
                ", deadLine=" + deadLine +
                '}';
    }
}
