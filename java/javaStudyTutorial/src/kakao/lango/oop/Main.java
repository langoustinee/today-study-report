package kakao.lango.oop;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        /*
        *  2022-12-14
        // Student 클래스의 인스턴스 생성하기
        Student stu1 = new Student();
        // 자신의 속성에 접근하기
        stu1.no = 1;
        stu1.name = "Joon";
        stu1.kor = 78;
        stu1.eng = 65;
        stu1.mat = 32;

        System.out.println(stu1.no + " " + stu1.name);
        System.out.println(stu1.kor + " " + stu1.eng + " " + stu1.mat);

        Student stu2 = new Student();
        stu2.no = 2;
        stu2.name = "Hong";

        // 인스턴스의 속성은 인스턴스 별로 소유하게 된다.
        System.out.println(stu1.no + " " + stu2.no);

        // 자바는 static 속성을 인스턴스가 접근할 수 있다.
        // static 속성은 모든 인스턴스가 공유하기 떄문에 가장 최근 수정한 데이터가 출력된다.
        stu1.schoolName = "원미고등학교";
        stu2.schoolName = "가남초등학교";
        System.out.println(stu1.schoolName + " "+ stu2.schoolName);
        */

        /*
        *  2022-12-15
        // static 메서드는 클래스 이름으로 호출할 수 있다.
        MethodClass.method1();
        // instance 메서드는 클래스이름으로 호출할 수 없다.
//        MethodClass.method2();

        // instance 메서드를 호출하기 위해서는 인스턴스를 먼저 만들어야 한다.
        MethodClass methodClass = new MethodClass();
        methodClass.method2();
        // Java는 인스턴스를 이용해서 static 메서드 호출도 가능하다.
        methodClass.method1();

        // 참조형 변수를 만들지 않고 인스턴스를 만들어서 메서드를 호출
        // 한 번만 사용하는 인스턴스는 아래와 같이 이름을 만들지 않는 것이 좋다.
        new MethodClass().method2();

        MethodClass.noArgsMethod();
        MethodClass.oneArgsMethod(3);
        MethodClass.twoArgsMethod(2, "2번 실행할게요!");
        MethodClass obj = new MethodClass();
        obj.addWithInteger1(5, 5);

        // return 결과 가져와 다른 작업을 수행할 후 있다.
        int sum = obj.addWithInteger2(2,3);
        sum = obj.addWithInteger2(sum, 90);
        System.out.println("sum2: " + sum);


        // 기본형 데이터를 메서드에게 넘겨준다면 기존 x는 변경되지 않는다.
        int x = 10;
        MethodClass.callByValue(x);
        System.out.println(x);

        // 참조형 데이터 배열을 메서드에게 넘겨주면 arr의 값은 변경될 수 있다.
        // 메서드의 return이 없는 경우라면 print 메서드를 제외하고 원본을 변경하게 된다.
        int[] arr = {1,2,3};
        MethodClass.callByReference(arr);
        System.out.println(Arrays.toString(arr));

        methodClass.thisMethod();


        int fibo = Fibonacci.loopFibo(50);
        System.out.println("loop fibo: " + fibo);

//        fibo = Fibonacci.recursionFibo(50);
//        System.out.println("recursion fibo: " + fibo);

        // VarArgs 클래스의 display 메서드의 매개변수가 String ... args인 가변으로 설정되어있으므로
        // 문자열을 몇개를 주어도 무방하다.
        VarArgs.display("JPA");
        VarArgs.display("Sequelize", "Mongoose");
        VarArgs.display("Carsandra");
        */
    }
}
