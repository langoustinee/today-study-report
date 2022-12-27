package kakao.lango.stream;

public class Articles {
    private int articleNo;
    private String category;
    private String title;
    private String content;
    private int scores;

    public Articles() {
        super();
    }

    public Articles(int articleNo, String category, String title, String content, int scores) {
        this.articleNo = articleNo;
        this.category = category;
        this.title = title;
        this.content = content;
        this.scores = scores;
    }

    public int getArticleNo() {
        return articleNo;
    }

    public void setArticleNo(int articleNo) {
        this.articleNo = articleNo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "Articles{" +
                "articleNo=" + articleNo +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", scores=" + scores +
                '}';
    }
}
