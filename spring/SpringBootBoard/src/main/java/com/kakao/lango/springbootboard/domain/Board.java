package com.kakao.lango.springbootboard.domain;

import com.kakao.lango.springbootboard.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "writer")
@Entity
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    private String title;
    private String content;

    // 처음에는 가져오지 않고 사용할 때 가져오는 지연로딩을 수행한다.
    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;

    // title을 수정하는 메서드
    public void changeTitle(String title) {
        if (title == null || title.trim().length() == 0) {
            this.title = "무제";
            return;
        }
        this.title = title;
    }

    // content를 수정하는 메서드
    public void changeContent(String content) {
        if (content == null || content.trim().length() == 0) {
            this.content = "냉무";
            return;
        }
        this.content = content;
    }
}
