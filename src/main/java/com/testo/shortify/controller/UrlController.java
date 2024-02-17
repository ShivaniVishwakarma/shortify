
package com.testo.shortify.controller;

import com.testo.shortify.dto.UrlLongRequest;
import com.testo.shortify.dto.UrlStats;
import com.testo.shortify.entity.Url;
import com.testo.shortify.service.UrlService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class UrlController {
    @Autowired
    private UrlService urlService;

    @PostMapping("create-short-url")
    //@PreAuthorize(value ="hasRole('USER') or hasRole('ADMIN')")
    public Url convertToShortUrl(@RequestBody @NonNull final UrlLongRequest request) {
        return urlService.shortenUrl(request);
    }

    @GetMapping("/redirect/{shortenedUrl}")
//    @PreAuthorize(value = "hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Void> redirectToLongUrl(@PathVariable @NonNull final String shortenedUrl) {
        String originalUrl = urlService.getOriginalUrlByShortenedUrl(shortenedUrl);
        if (originalUrl != null) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create(originalUrl))
                    .build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/stats/{shortUrl}")
    public UrlStats getUrlStats(@PathVariable @NonNull final String shortUrl) {
        return urlService.getUrlStats(shortUrl);
    }

    @GetMapping("/getAllUrls")
    public List<Url> getAllUrls() {
        return urlService.getAllUrls();
    }

}