package com.kakao.lango.guestbook.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// 테이블을 생성하지 않는 Entity로 만들어주는 어노테이션
@MappedSuperclass
// Entity의 변화를 감지하는 어노테이션
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
public class BaseEntity {
    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regdate;

    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;

}
