package kakao.lango.operator;

public class BitLogical {
    public static void main(String[] args) {
//        int a = 20;
//        int b = 17;
//
//        System.out.println(a & b);
//        System.out.println(a | b);
//        System.out.println(a ^ b);


        // String VS StringBUilder
        long start = System.currentTimeMillis();
//        String test = "aa";
        StringBuilder test = new StringBuilder("aa");
        for (int i = 0; i <100000; i++) {
//            test= test+"aa";
             test.append("aa");
        }
        long end = System.currentTimeMillis()-start;
        System.out.println(end);
    }
}
