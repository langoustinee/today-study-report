package kakao.lango.oop;

public class Data {
    private static int sequence;
    // MySQL의 Auto_increament와 동일한 동작
    static {
        sequence = 1;
    }
    private int num;
    private String name;

    // Default Constructor
    public Data() {
        super();
        // sequence를 num에 대입하고 1 증가시킨다.
        num = sequence++;
    }

    // Constructor
    public Data(String name) {
        this.num = sequence++;
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Data{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }
}
