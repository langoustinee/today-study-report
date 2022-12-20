package kakao.lango.java.lang;

public class FastEnumerationMain {
    public static void main(String[] args) {
        // 기본값 0을 가지고 1000000개의 데이터를 가진 배열 생성하기
        int[] arr = new int[1000000];

        long start = System.currentTimeMillis();

        // 인덱스를 이용한 순회
//        for(int i=0; i<arr.length; i++) {
//            System.out.println(arr[i]);
//        }
        // 빠른 열거 사용
        for(int temp : arr) {
            System.out.println(temp);
        }

        long end = System.currentTimeMillis();
        System.out.println("실행 시간은 " + (end-start) + "밀리초 입니다.");
    }
}
