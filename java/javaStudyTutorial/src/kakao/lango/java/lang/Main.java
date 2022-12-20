package kakao.lango.java.lang;

public class Main {
    public static void main(String[] args) {
        String[] imgs = {"uuid_1.jpg", "uuid_2.jpg", "uuid_3.jpg"};

        // 인스턴스 생성하기
        Data original = new Data(1, "제목입니다.", "내용입니다.", imgs);

        // 인스턴스의 참조를 복사하기
        // 참조 대상이나 원본이 내부 데이터를 변경하면 원본에 영향이 생긴다.
        Data data = original;
        System.out.println(original);
        // 참조를 복사하게되면 data를 변경했는데 original까지 변경된다.
        data.setPostId(2);
        System.out.println(original);

        // 자바는 복제를 이용하고자 하는 경우 clone 메서드를 재정의하여 사용하면 된다.
        // 재정의 할 때, CLoneable 인터페이스를 implements 해야 한다.

        Data copy = original.clone();
        System.out.println(original);
        copy.setPostId(3);
        String[] arr = copy.getImages();
        arr[0] = "uuid_4.jpg";
        System.out.println(original);

        Data data1 = new Data(2,"title", "content", null);
        Data data2 = new Data(2,"title", "content", null);
        // == 는 인스턴스를 비교하는 연산자이다.
        // 2개의 인스턴스는 각각 생성자를 호출하여 만들었기에 참조가 달라 false이다.
        // 내용이 같은지 비교하고자 할 떄는 equals 메서드를 재정의하여 사용해야 한다.
        System.out.println(data1 == data2);
        System.out.println(data1.equals(data2));
    }
}
