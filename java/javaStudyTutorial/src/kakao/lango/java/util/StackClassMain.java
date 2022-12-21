package kakao.lango.java.util;

import java.util.Stack;

public class StackClassMain {
    public static void main(String[] args) {
        // 하나의 스택 생성하기
        Stack<String> st = new Stack<>();
        st.push("굴리트");
        st.push("호나우두");
        st.push("메시");
        st.push("반페르시");
        // 데이터는 List처럼 순차적으로 삽입한다.
        System.out.println(st);
        // pop을 하면 가장 마지막 삽입된 데이터를 삭제하면서 리턴한다.
        System.out.println(st.pop());
        System.out.println(st.pop());



    }
}
