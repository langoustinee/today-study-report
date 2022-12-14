package kakao.lango.datatype;

public class FormatDisplay {
    public static void main(String[] args) {
        int n = 77;
        double d = 33.5678;
        String str1 = "Hello Java!";
        String str2 = "oppps!";

        System.out.printf("d:%d\n", n);
        // 10자리를 확보하여 출력
        System.out.printf("d:%10d\n", n);
        // 남는 자리는 0을 추가하여 출력
        System.out.printf("d:%010d\n", n);

        // 소수 6째 자리까지 출력
        System.out.printf("d:%fd\n", d);
        // 소수 3째 자리까지 출력 - 반올림
        System.out.printf("d:%.3fd\n", d);

        // %s는 문자열을 출력한다.
        // 원래 %s는 시작위치부터 바이트 단위로 데이터를 가져와서 문자로 변환한 후 출력하는 것으로 null을 만날때까지 출력한다.
        System.out.printf("str1:%10s\n", str1);
        System.out.printf("str2:%10s\n", str2);
    }
}
