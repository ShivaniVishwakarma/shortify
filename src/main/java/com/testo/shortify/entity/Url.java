package com.testo.shortify.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "url")
@Data
public class Url extends AbstractEntity<Long>{
    private String originalUrl;
    private String shortenedUrl;
    private int shorteningCount;
    private int accessCount;

    public Url() {
    }

    public Url(String originalUrl, String shortenedUrl) {
        this.originalUrl = originalUrl;
        this.shortenedUrl = shortenedUrl;
        this.shorteningCount = 0;
        this.accessCount = 0;
    }

}

