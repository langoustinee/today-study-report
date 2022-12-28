package kakao.lango.dto;

public class Good {
    private String code;
    private String name;
    private String menufacture;
    private int price;

    public Good() {
        super();
    }

    public Good(String code, String name, String menufacture, int price) {
        this.code = code;
        this.name = name;
        this.menufacture = menufacture;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenufacture() {
        return menufacture;
    }

    public void setMenufacture(String menufacture) {
        this.menufacture = menufacture;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Good{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", menufacture='" + menufacture + '\'' +
                ", price=" + price +
                '}';
    }
}
