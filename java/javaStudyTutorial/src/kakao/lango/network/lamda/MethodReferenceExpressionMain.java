package kakao.lango.network.lamda;

import java.util.Arrays;
import java.util.List;

public class MethodReferenceExpressionMain {
    public static void main(String[] args) {
        List<String> articles = Arrays.asList("1번글", "2번글", "3번글");

        articles.forEach(article -> System.out.println(article));
        articles.forEach(System.out::println);
    }
}
