package kakao.lango.array;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {

        // 선택 정렬 구현
        int[] arr = {1, 5, 3, 2, 4};
        // 첫번째부터 마지막 앞 자리까지
        for(int i=0; i<arr.length-1; i++) {
            // 기준의 뒤부터 마지막 자리까지
            for(int j=i+1; j<arr.length; j++) {
                // 자리의 데이터를 비교해서 기준자리의 데이터가 더 클 경우 두 데이터를 교환한다.
                if(arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            System.out.println(Arrays.toString(arr));
        }
    }
}
