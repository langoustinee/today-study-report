package kakao.lango.oop;

public class VarArgs {

    // 매개변수를 가변으로 설정
    public static void display(String ... args) {
        for (String str : args) {
            System.out.println(str);
        }
    }
}
