package com.moshimoshi.common.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

//    @CreatedBy
//    @Column(updatable = false, nullable = false)
//    private String createdBy;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

//    @LastModifiedBy
//    private String modifiedBy;

    private LocalDateTime deletedAt;
    private String deletedBy;
}