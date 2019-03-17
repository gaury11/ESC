package com.esc.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드들(regDate, uptDate)도 컴럼으로 인식하도록 함
@EntityListeners(AuditingEntityListener.class) //
public abstract class BaseTimeEntity {

    /*
    @EntityListeners(AuditingEntityListener.class) : BaseTimeEntity 클래스에 Auditing 기능을 포함시킴

    @CreateDate : Entity 가 생성되어 저장될 때 시간이 자동 저장됨

    @LastModifiedDate : 조회한 Entity 의 값을 변경할 때 시간이 자동 저장됨

    */

    @Column(name = "REG_DATE")
    @CreatedDate
    private LocalDateTime regDate;

    @Column(name = "UPT_DATE")
    @LastModifiedDate
    private LocalDateTime uptDate;
}
