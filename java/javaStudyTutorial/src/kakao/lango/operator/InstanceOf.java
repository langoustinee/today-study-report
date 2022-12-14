package kakao.lango.operator;

import java.util.Arrays;

public class InstanceOf {
    static class  Data {

        public Data(int i) {
        }
    }
    public static void main(String[] args) {
        int[] arr = {100, 500, 250, 50};

        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        Data[] drr = {new Data(100), new Data(50), new Data(500)};

    }
}
