package com.testo.shortify.entity;

import com.testo.shortify.util.DateTimeUtil;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

    @Access(AccessType.FIELD)
    @MappedSuperclass
    @Data
    public abstract class AbstractEntity<T> implements Serializable {

        /**
         * this model will be used as a parent model for generating the unique id  and storing the created at and updated at.
         */
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "created_at", nullable = false)
        private Long createdAt;

        @Column(name = "updated_at", nullable = false)
        private Long updatedAt;


        @PreUpdate
        protected void onUpdate() {
            this.updatedAt = DateTimeUtil.epochTimeNow();
        }

        @PrePersist
        protected void onCreate() {
            this.updatedAt = DateTimeUtil.epochTimeNow();
            this.createdAt = DateTimeUtil.epochTimeNow();
        }

    }

