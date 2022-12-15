package kakao.lango.oop;

public class MethodClass {

    private static int num;
    private String name;

    // sttic 메서드 작성
    public static void method1() {
        num = 1;
        // satic 메서드에서는 this가 없기 떄문에 instance의 속성을 사용할 수 없다.
//        name = "ddd";
        System.out.println("static 메서드가 실행되었습니다.");
    }

    // instance 메서드 작성
    public void method2() {
        // instance 메서드에서는 this가 있기 때문에 name에 접근 가능하다.
        num = 1;
        name = "lango";

        System.out.println("instance 메서드가 실행되었습니다.");
    }

    // static 메소드는 인스턴스를 생성할 필요가 없다.
    // 매개변수가 없다.
    // MethodClass.noArgMethod()로 호출하면 된다.
    public static void noArgsMethod() {
       for(int i=0; i<5; i++) {
           System.out.println("No Argument Method!");
       }
    }

    // 매개변수가 1개인 메서드
    // MethodClass.oneArgsMethod(5)로 호출하면 된다.
    public static void oneArgsMethod(int n) {
        for(int i=0; i<n; i++) {
            System.out.println("One Argument Method!");
        }
    }

    // 매개변수가 2개인 메서드
    // 매개변수가 2개 이상인 경우는 순서대로 대입해야한다.
    public static void twoArgsMethod(int n, String msg) {
        for(int i=0; i<n; i++) {
            System.out.println(msg);
        }
    }

    // return이 없는 메서드는 연속으로 호출할 수 없다.
    // 2개의 정수를 받아 합계를 구하는 메서드
    // static 키워드가 붙지 않았기에 인스턴스.addWithInteger(정수1, 정수2) 형식으로 호출해야 한다.
    // 2개의 정수 합계를 출력할 수는 있지만 재사용 할 수 는 없다.
    public void addWithInteger1(int first, int secend) {
        System.out.println("sum: " + (first+secend));
    }

    // 2개의 매개변수를 받아 합계를 리턴해주는 메서드
    public int addWithInteger2(int first, int secend) {
        return (first+secend);
    }

    // 오버로딩 구현
    // 메서드의 매개변수 자료형을 다르게 하여 구현할 수 있다.
    public void display() {

    }
    // 매개변수의 개수가 달라서 오버로딩 가능
    public void display(int n) {

    }
    // 매개변수의 개수는 같지만 자료형이 달라서 오버로딩 가능
    public void display(String msg) {

    }
    // 매개변수 개수 같지만 자효형이 같기에 오버로딩 불가
//    public void display(String msg2) {
//
//    }

    // 매개변수 기본형 1개일 경우
    // 내부에서 매개변수의 값을 수정해도 넘겨준 데이터는 변경되지 않는다.
    public static void callByValue(int n) {
        n = n + 1;
        System.out.println("n: " + n);
    }

    // 매개변수가 참조형일 경우
    public static void callByReference(int[] arr) {
        arr[0] = arr[0] + 1;
        System.out.println("arr[0]: " + arr[0]);
    }

    public int score = 100;
    public void thisMethod() {
        int score = 200;
        // scope의 법칙이 적용된다.
        // 동일한 이름이 여러개 존재할 때 가장 가까운 쪽에서 생성되거나 최근에 생성된 것을 사용한다.
        // 200이 출력된다.
        System.out.println("method score: " + score);
        // 인스턴스 socre가 가진 100을 출력
        // this가 붙는다면 메서드 안에 있는 지역변수는 찾지 않는다.
        System.out.println("instance score: " + this.score);
    }

 }
