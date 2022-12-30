package kakao.lango.studylocallogin.domain;

public class UserVO {
    private String userid;
    private String password;
    private String nickname;
    private String uuid;

    public UserVO() {
        super();
    }

    public UserVO(String userid, String password, String nickname) {
        this.userid = userid;
        this.password = password;
        this.nickname = nickname;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "userVO{" +
                "userid='" + userid + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
