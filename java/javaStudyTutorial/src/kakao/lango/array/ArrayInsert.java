package kakao.lango.array;

import java.util.Arrays;

public class ArrayInsert {
    public static void main(String[] args) {
        String[] menu = {"짜장면", "볶음밥", "짬뽕", "탕수육"};

        // 직접 복제
        String[] copy = new String[menu.length+1];

        // 배열의 요소 복제
        for(int i=0; i<menu.length; i++) {
            copy[i] = menu[i];
        }
        System.out.println(Arrays.toString(copy));

        // 배열 자체를 복제
        String[] copy2 = Arrays.copyOf(menu, menu.length+1);
        copy2[4] = "유산슬";
        System.out.println(Arrays.toString(copy2));




    }
}
