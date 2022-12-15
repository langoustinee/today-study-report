package kakao.lango.oop;

public class Student {
    // static 속성 사용하기
    public static String schoolName;

    // static 초기화 블록
    static {
        System.out.println("Hello Student!");
        // 이 블록에서는 static 변수를 사용할 수 있다.
        schoolName = "";
    }

    public final int schoolNo;

    public Student(int schoolNo) {
        this.schoolNo = schoolNo;
    }

    // 인스턴스의 속성을 생성하기
    // 접근제한자를 public이기에 외부에서 인스턴스를 통해 속성에 접근할 수 있다.
    // protected를 이해하고 잘 사용할 줄 알아야 한다.
    protected int no;
    public String name;
    public int kor;
    public int eng;
    public int mat;

    // 생성자 생성하기
//    public Student(int no, String name, int kor, int eng, int mat) {
//        this.no = no;
//        this.name = name;
//        this.kor = kor;
//        this.eng = eng;
//        this.mat = mat;
//    }
}
