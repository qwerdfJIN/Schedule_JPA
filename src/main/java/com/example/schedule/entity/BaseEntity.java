package com.example.schedule.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

//공통 Entity (작성일, 수정일 자동 관리)
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    //작성일 (수정 불가)
    @CreatedDate
    @Column(updatable = false)
    private LocalDate createdAt;

    //수정일
    @LastModifiedDate
    private LocalDate modifiedAt;
}
