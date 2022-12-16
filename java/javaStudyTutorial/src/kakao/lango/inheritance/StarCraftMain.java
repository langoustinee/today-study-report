package kakao.lango.inheritance;

public class StarCraftMain {
    public static void main(String[] args) {
        // protoss 공격 호출
        Protoss protoss = new Protoss();
        protoss.attack();

        // zerg 공격 호출
        Zerg zerg = new Zerg();
        zerg.attack();

        // terran 공격 호출
        Terran terran = new Terran();
        terran.attack();

        // protoss 공격 호출
        Starcraft star = new Protoss();
        star.attack();
        // zerg 공격 호출
        star = new Zerg();
        star.attack();
        // terran 공격 호출
        star = new Terran();
        star.attack();

        // 상속만 해주려고 만들었기에 상위클래스에서 attck 메서드를 호출하면 에러가 발생해야 하지만 에러가 발생하지 않는다.
        //        star = new Starcraft();
        //        star.attack();

        // 추상클래스는 인스턴스 생성을 할 수 없기에 new를 할 경우 에러가 발생한다.
        //        star = new Starcraft();
        //        star.attack();






    }
}
