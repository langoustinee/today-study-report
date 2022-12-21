package kakao.lango.java.util;

import java.util.Arrays;

public class QuickSortMain {
    // quick sort를 위한 메서드이다.
    // left는 비교의 시작 위치이며 right는 비교의 반대편 끝 위치를 뜻한다.
    // data는 정렬할 배열이다.
    public static void quickSort(int left, int right, int[] data) {
        // 데이터 출력
        System.out.println(Arrays.toString(data));

        // 기준점 설정하기
        // 알고리즘을 설명할 때는 임의의 위치나 중앙이라고 하는데, 구현할 때는 맨 왼쪽 값을 기준으로 설정한다.
        int pivot = left;

        // 작은 데이터를 찾기 위한 인덱스는 right이다.
        // 큰 데이터를 찾기 위한 인덱스 i 선언하기
        int i = left + 1;

        // 나중에 데이터를 교체해야 하기에 pivot의 위치를 저장한다.
        int j = pivot;

        // data 배열의 데이터가 2개 이상인 경우만 수행한다.
        // 배열의 데이터가 1개이면 left와 right가 같은 값을 바라보기에 같기 때문이다.
        if(left < right) {
            // 1번의 회전을 수행한다.
            for(; i<=right; i++) {
                if(data[i] < data[pivot]) {
                    j = j + 1;
                    // Swap(데이터 교환)을 진행한다.
                    int temp = data[j];
                    data[j] = data[i];
                    data[i] = temp;
                }
            }
            // pivot 위치의 데이터를 자신의 위치로 이동시킨다.
            int temp = data[left];
            data[left] = data[j];
            data[j] = temp;

            // pivot의 위치를 비교가 끝난 자리로 수정한다.
            pivot = j;
            // pivot의 왼쪽 위치를 재귀적으로 다시 퀵 정렬을 수행한다.
            quickSort(left, pivot-1, data);
            // pivot의 오른쪽 위치를 재귀적으로 다시 퀵 정렬을 수행한다.
            quickSort(pivot+1, right, data);
        }
    }

    public static void main(String[] args) {
        int[] arr = {56,3,2,78,45,22,85};
        quickSort(0,arr.length-1, arr);
        System.out.println("오름차순 정렬 후: " + Arrays.toString(arr));
    }

}
