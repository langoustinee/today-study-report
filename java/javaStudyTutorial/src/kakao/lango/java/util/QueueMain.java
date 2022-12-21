package kakao.lango.java.util;

import java.util.PriorityQueue;
import java.util.Queue;

public class QueueMain {
    public static void main(String[] args) {

        Queue<String> qu = new PriorityQueue<>();
        qu.offer("리오넬 메시");
        qu.offer("크리스티아누 호날두");
        qu.offer("루드 굴리트");
        qu.offer("로빈 반페르시");
        qu.offer("다니엘 제임스");
        qu.offer("폴 포그바");

        System.out.println(qu);

        qu.offer("농구");
        System.out.println(qu.poll());
        
        
    }
}
