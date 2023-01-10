package com.kakao.lango.springbootjpa.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_memo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class Memo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mno;

    @Column(length = 200, nullable = false)
    private String memoText;

}
