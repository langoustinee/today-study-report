package kakao.lango.csv;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SuperCSVMain {
    public static void main(String[] args) {
        // 저장할 List 생성하기
        List<Posts> list = new ArrayList<>();

        // 읽을 파일의 경로 생성하기
        Path path = Paths.get("./volley.csv");
        // 제약조건 설정하기
        CellProcessor[] processors = new CellProcessor[] {
                new ParseInt(new NotNull()), // postId
                new NotNull(), // title
                new NotNull(), // content
                new Optional(), // img
                new ParseInt(new NotNull()), // like
                new ParseDate("yyyy-MM-dd"), // deadLine
        };

        // CSV를 읽기 위한 경로를 생성하기
        try(ICsvBeanReader beanReader = new CsvBeanReader(Files.newBufferedReader(path), CsvPreference.STANDARD_PREFERENCE)) {
            // 헤더(첫줄) 읽어오기
            String[] header = beanReader.getHeader(true);
            System.out.println(Arrays.toString(header));

            // 데이터를 읽어서 list에 추가하기
            Posts post = null;
            // 한줄 씩 읽어서 header에 맞춰서 Post 클래스 타입의 인스턴스를 생성한다.
            while ((post = beanReader.read(Posts.class, header, processors)) != null) {
                list.add(post);
            }
            for (Posts data : list) {
                System.out.println(data);
            }

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
