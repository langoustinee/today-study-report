package kakao.lango.java.util;

// 데이터 클래스 VO 만들기
public class CommentVO {

    private int commentId;
    private String content;

    public CommentVO() {
        super();
    }

    public CommentVO(int commentId, String content) {
        super();
        this.commentId = commentId;
        this.content = content;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommentVO{" +
                "commentId=" + commentId +
                ", content='" + content + '\'' +
                '}';
    }
}
