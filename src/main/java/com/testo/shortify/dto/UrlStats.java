package com.testo.shortify.dto;

import lombok.Data;

@Data
public class UrlStats {
    private long shortenCount;
    private long accessCount;

    public UrlStats(long shortenCount, long accessCount) {
        this.shortenCount = shortenCount;
        this.accessCount = accessCount;
    }

}