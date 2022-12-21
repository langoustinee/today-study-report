package kakao.lango.java.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapMain2 {
    public static void main(String[] args) {
        // 데이터 생성하기 - Model
        CommentVO vo = new CommentVO(1, "저는 A가 좋은 것 같습니다.");

        // 데이터 출력하기 - View
        // 모델의 근원이 되는 VO 클래스 안에 속성이름을 변경하면 View도 수정이 되어야 한다.
        // 관계형 데이터베이스는 VO 클래스를 활용한다.
        System.out.println("댓글 번호: " + vo.getCommentId());
        System.out.println("댓글 내용: " + vo.getContent());
        
        // VO 클래스의 인스턴스 역할을 수행하는 Map을 작성한다.
        Map<String, Object> hm = new HashMap<>();
        // 데이터 저장하기
        hm.put("commentId", 1);
        hm.put("content", "저는 A가 좋은 것 같습니다.");

        // map의 모든 키를 가져와서 출력하기
        Set<String> keys = hm.keySet();
        for(String key : keys) {
            System.out.println(key + " " + hm.get(key));
        }
    }
}
