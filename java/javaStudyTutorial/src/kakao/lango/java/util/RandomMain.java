package kakao.lango.java.util;

import java.util.Random;

public class RandomMain {
    public static void main(String[] args) {
        // 1 ~ 45 사이의 랜덤한 정수 가져오기
        Random random = new Random();
        int lotto = random.nextInt(45)+1;
        System.out.println(lotto);

        String[] card = {"스페이드", "다이아몬드", "하트", "클로버"};
        String myCard = card[random.nextInt(card.length)];
        System.out.println(myCard);
    }
}
