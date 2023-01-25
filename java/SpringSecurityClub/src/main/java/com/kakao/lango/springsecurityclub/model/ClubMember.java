package com.kakao.lango.springsecurityclub.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "roleSet")
@Entity
public class ClubMember extends BaseEntity {
    @Id
    private String mid;
    private String mpw;
    private String email;
    private String name;

    private boolean social;
    private boolean del;

    // 권한의 경우 여러개의 권한을 소유할 수 있다.
    // 리스트같은 객체는 null로 두면 안되기 때문에 기본값을 설정해주어야 하는데 이 때, @Builder.Default 어노테이션을 사용한다.
    // 해당 컬렉션에 지연로딩을 적용했기 때문에 ToString 메서드에서 제외를 시켜주어야 핝다.
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<ClubMemberRole> roleSet = new HashSet<>();

    /**
     * 비밀번호 수정같은 기능들은 setter를 만들지 말고 필요한 메서드를 만들면 된다.
     * PK로 설정된 mid는 수정할 수 없도록 하는 것이 원칙이다.
     */
    // 비밀번호 수정
    public void changePassword(String mpw) {
        this.mpw = mpw;
    }
    // 이메일 수정
    public void changeEmail(String email) {
        this.email = email;
    }
    // 탈퇴여부 수정
    public void changeDel(boolean del) {
        this.del = del;
    }
    // 권한 추가
    public void addRole(ClubMemberRole role) {
        this.roleSet.add(role);
    }
    // 모든 권한 삭제
    public void clearRoles() {
        this.roleSet.clear();
    }

    // 소셜 로그인 가입 여부
    public void changeSocial(boolean social) {
        this.social = social;
    }

}
