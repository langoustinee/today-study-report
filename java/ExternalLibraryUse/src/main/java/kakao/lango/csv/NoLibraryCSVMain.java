package kakao.lango.csv;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoLibraryCSVMain {
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./volley.csv")))) {
            // 파일의 경로 확인하기
            System.out.println(br);
            // 첫 줄은 데이터가 아니기에 배제하기 위한 변수 생성하기
            boolean flag = false;
            // 파싱한 결과를 저장하기 위한 List
            List<Posts> list = new ArrayList<>();

            while (true) {
                String line = br.readLine();
                if(line == null) break;
                // 첫 줄은 읽지 안도록 하기
                if (flag == false) {
                    flag = true;
                    continue;
                }
//                System.out.println(line);
                // 콤머, 단위로 분할하기
                String[] arr = line.split(",");
//                System.out.println(arr[0]);

                Posts posts = new Posts();
                posts.setPostId(Integer.parseInt(arr[0]));
                posts.setTitle(arr[1]);
                posts.setContent(arr[2]);
                posts.setImg(arr[3]);
                posts.setLike(Integer.parseInt(arr[4]));
                // Date로 변환하여 날짜값 삽입하기
                String deadLine = arr[5];
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(deadLine);
                posts.setDeadLine(date);

                // 리스트에 추가하기
                list.add(posts);
            }
            for (Posts post : list) {
                System.out.println(post);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
