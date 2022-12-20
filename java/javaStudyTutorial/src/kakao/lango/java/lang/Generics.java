package kakao.lango.java.lang;

// 미지정 자료형의 이름은 한 글자로 하는 것이 일반적이다.
public class Generics <T> {
    private T[] dataList;

    // ...은 데이터 개수에 상관없이 매개변수로 받아서 배열로 만들어주는 문법이다. - varargs
    public Generics(T ... n) {
        dataList = n;
    }

    // 배열의 데이터를 출력해주는 메서드
    public void display() {
        for(T temp : dataList) {
            System.out.println(temp);
        }
    }
}
