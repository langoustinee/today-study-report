package kakao.lango.java.util;

import java.util.Arrays;

public class ArraysMain {
    public static void main(String[] args) {
        // 정수 배열 만들기
        int[] IntegerArr = {50, 20, 1, 65, 0, 16};
        // 문자열 배열 만들기
        String[] StringArr = {"postId", "title", "content", "imgs"};

        System.out.println(Arrays.toString(IntegerArr));
        System.out.println(Arrays.toString(StringArr));

        // 정수 배열 정렬
        Arrays.sort(IntegerArr);
        System.out.println(Arrays.toString(IntegerArr));

        // 문자열 배열 정렬
        Arrays.sort(StringArr);
        System.out.println(Arrays.toString(StringArr));

        // VO 클래스의 인스턴스 5개를 소유하는 배열 만들기
        String[] images = {"uuid_1.jpg","uuid_2.jpg","uuid_3.jpg"};
        ArraysVO[] datas = new ArraysVO[5];
        datas[0] = new ArraysVO(1, "제목1", "내용1", images);
        datas[1] = new ArraysVO(2, "제목2", "내용2", images);
        datas[2] = new ArraysVO(3, "제목3", "내용3", images);
        datas[3] = new ArraysVO(4, "제목4", "내용4", images);
        datas[4] = new ArraysVO(5, "제목5", "내용5", images);
        System.out.println(Arrays.toString(datas));

        // Arrays.sort(datas)할 경우 ClassCastException 에러 발생
        Arrays.sort(datas);
        System.out.println(Arrays.toString(datas));

    }
}
