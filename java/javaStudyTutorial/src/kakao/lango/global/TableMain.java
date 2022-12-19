package kakao.lango.global;

public class TableMain {
    public static void main(String[] args) {
        // auto_increments 속성을 가지는 컬럼을 추가하는 것과 동일하다.
        Table row1 = new Table();
        System.out.println(row1.getNum());
        Table row2 = new Table();
        System.out.println(row2.getNum());

        Table.setStep(10);
        Table row3 = new Table();
        System.out.println(row3.getNum());
    }
}
