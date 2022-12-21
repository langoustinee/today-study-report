package kakao.lango.java.util;

import java.util.Arrays;

public class BinarySearchMain {
    public static void main(String[] args) {
        int[] arr = {50,20,10,30,40};

        // 20을 찾기 위해 binarySearch를 사용하였지만 음수가 나오기에 없다는 것이다.
        // 배열을 오름차순 정렬해두지 않으면 정확한 결과가 나오지 않는다.
        System.out.println(Arrays.binarySearch(arr, 20));

        // 배열을 오름차순 정렬하기
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        // 정렬된 상태이기에 20은 1, 30은 2의 위치를 반환한다.
        System.out.println(Arrays.binarySearch(arr, 20));
        System.out.println(Arrays.binarySearch(arr, 30));
    }
}
