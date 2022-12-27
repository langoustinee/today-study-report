package kakao.lango.json;

public class ToDoVO {
    private Integer userid;
    private Integer id;
    private String title;
    private Boolean completed;

    public ToDoVO() {
        super();
    }

    public ToDoVO(Integer userid, Integer id, String title, Boolean completed) {
        this.userid = userid;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "ToDoVO{" +
                "userid=" + userid +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
