package kakao.lango.java.lang;

public class GenericsMain {
    public static void main(String[] args) {

        Generics<String> obj1 = new Generics<>("Java", "Spring", "Node", "React");
        obj1.display();

        // 기본형 타입은 제네릭에 적용할 수 없기에 래퍼클래스로 사용해야 한다.
        Generics<Integer> obj2 = new Generics<Integer>(56,1,20,4,32);
        obj2.display();

    }
}
