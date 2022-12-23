package kakao.lango.java.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LogMain {
    public static void main(String[] args) {
        // 파일의 경로에서 파일의 존재 여부 확인한다. - t/f
        File file = new File("./log.txt");
        System.out.println(file.exists());

        // ip 별로 합계할 구할 Map 생성
        Map<String, Integer> hm = new HashMap<String, Integer>();

        // 문자열을 읽기 위한 스트림을 생성한다.
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // 트래픽의 합계를 구할 변수
            int sum = 0;
            // 줄 단위로 읽어서 출력한다.
            while (true) {
                String log = br.readLine();
                if (log == null) {
                    System.out.println("읽은 파일이 없습니다.");
                    break;
                }
                // 공백을 기준으로 분할한다.
                String[] arr = log.split(" ");
                // 트래픽 데이터 확인하기
                System.out.println(arr[arr.length-1]);

                // 트래픽 데이터에 -가 포함되지 않은 숫자로 된 문자열만 집계한다.
                // if문으로 집계하기
                if(!arr[arr.length-1].contains("-")) {
                    // 트래픽을 정수로 변환하여 더하기
                    sum += Integer.parseInt(arr[arr.length-1]);
                    // ip별 트래픽 합계 구하기
                    hm.put(arr[0], hm.getOrDefault(arr[0],0)+Integer.parseInt(arr[arr.length-1]));
                }
                // try catch문으로 집계하기
//                try {
//                    sum += Integer.parseInt(arr[arr.length-1]);
//                } catch (Exception e) {
//
//                }
                
            }
            System.out.println("트래픽의 합계: " + sum);
            System.out.println(hm);
            int sum2 = 0;
            for (Map.Entry<String, Integer> map : hm.entrySet()) {
                sum2 += map.getValue();
            }
            System.out.println("Map 트래픽의 합계: " + sum2);

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }

    }
}
