package com.kakao.lango.springbootboard.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Table(name = "tbl_member")
@Entity
public class Member extends BaseEntity {
    @Id
    private String email;

    private String password;

    private String name;
}
