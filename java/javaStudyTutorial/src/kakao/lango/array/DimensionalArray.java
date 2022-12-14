package kakao.lango.array;

public class DimensionalArray {
    public static void main(String[] args) {
        // 2차원 배열
        String[][] skills = {
                {"올려치기", "내려치기"},
                {"가로베기", "쓸어베기"},
                {"파이어볼", "아이스월"}
        };

        // 행의 개수
        System.out.println(skills.length);
        // 열의 개수
        System.out.println(skills[0].length);

        for(String[] skill : skills) {
            for(String skillName : skill) {
                System.out.print(skillName + " ");
            }
            System.out.println();
        }

        String[] skill = {"올려치기", "내려치기", "가로베기", "쓸어베기", "파이어볼", "아이스월"};
        for(int i=0; i<skill.length; i++) {
            System.out.print(skill[i] + " ");
            if(i % 3 == 2) System.out.println();
        }

    }
}
