package com.example.demo.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BasicEntityAudit<ID> extends BasicEntity<ID> implements Serializable {
    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @LastModifiedBy
    @Column(name = "update_by")
    private String updatedBy;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_by")
    private String deletedBy;
    @Column(name = "deleted_at")
    private Date deletedAt;

    @Column(nullable = false, name = "is_deleted")
    private Boolean isDeleted = false;

    @PreUpdate
    @PrePersist
    public void beforeAnyUpdate() {
        if (isDeleted != null && isDeleted) {

            if (deletedBy == null) {
                // TODO:: complete later with security
                deletedBy = "ali";
            }

            if (getDeletedAt() == null) {
                deletedAt = new Date();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasicEntityAudit that)) return false;
        if (!super.equals(o)) return false;
        return createdBy.equals(that.createdBy) &&
                updatedBy.equals(that.updatedBy) &&
                createdAt.equals(that.createdAt) &&
                updatedAt.equals(that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), createdBy, updatedBy, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "BaseEntityAudit{" +
                "createdBy='" + createdBy +
                ", updatedBy='" + updatedBy +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", isDeleted=" + isDeleted +
                "}" +
                super.toString();
    }
}