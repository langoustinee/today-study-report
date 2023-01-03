public class First {
    public static int addPoint(int point, int getPoint) {
        return point + getPoint;
    }
    public static void main(String[] args) {
        System.out.println("Hello Java!");

        String msg = "Hello";
        int n = 10;

        int point = 5;
        int getPoint = 1;

        addPoint(point, getPoint);

        int res = (point, getPoint) -> point + getPoint;

        System.out.println(msg+n);
    }
}