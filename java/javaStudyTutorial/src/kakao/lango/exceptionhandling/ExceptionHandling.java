package kakao.lango.exceptionhandling;

public class ExceptionHandling {
    public static void main(String[] args) {
        // 예외를 발생시키기
        int a = 0;
        int b = 0;
        // java.lang.ArithmeticException: / by zero 예외 발생
        try {
            // 예외가 발생할 가능성이 있는 내용을 작성
            System.out.println(a/b);
        }
        // catch가 여러개일 경우 일치하는 catch 블록의 내용만 처리하고 다른 catch는 수행하지 않는다. (if문 처럼 동작)
        // 상위 클래스의 참조형 변수에는 하위클래스 인스턴스 참조를 저장할 수 있기 때문에 catch를 여러개 작성할 떄는 주의해야 한다.
        // 상위클래스인 Exception의 catch 구문을 만들면 하위클래스 ArithmeticException의 catch구문은 dead code가 된다.
        catch (Exception e) {
            // 예외가 발생했을 때 수행할 내용을 작성
            // 예외가 발생하지 않으면 수행되지 않는다.
            System.out.println("NULL이 다른 속성이나 메서드를 호출하면 예외가 발생한다. " + e.getMessage());
        }
//        catch (ArithmeticException e) {
//            System.out.println("0으로 나누면 안된다. " + e.getMessage());
//        }
        finally {
            // 예외 발생 여부에 관계없이 수행된다.
            System.out.println("a를 b로 나눈다.");
        }

    }
}
