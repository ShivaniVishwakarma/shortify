package com.testo.shortify.service;


import com.testo.shortify.dto.UrlLongRequest;
import com.testo.shortify.dto.UrlStats;
import com.testo.shortify.entity.Url;
import com.testo.shortify.exception.NotFoundException;
import com.testo.shortify.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORTENED_URL_LENGTH = 6;

    private Map<String, String> urlMap = new HashMap<>();

    public Url shortenUrl(UrlLongRequest request) {
        // Check if the URL already exists in the database
        Url url = urlRepository.findByOriginalUrl(request.getLongUrl());
        if (url != null) {
            url.setShorteningCount(url.getShorteningCount() + 1);
            return urlRepository.save(url);
        }

        // Generate a unique shortened URL
        String shortenedUrl = generateRandomUrl();
        while (urlMap.containsKey(shortenedUrl)) {
            shortenedUrl = generateRandomUrl();
        }

        // Save the original URL and shortened URL to the database
        url = new Url(request.getLongUrl(), shortenedUrl);
        urlMap.put(shortenedUrl, request.getLongUrl());
        return urlRepository.save(url);
    }

    private String generateRandomUrl() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < SHORTENED_URL_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    public String getOriginalUrlByShortenedUrl(String shortenedUrl) throws NotFoundException {
        Optional<Url> urlMappingOptional = urlRepository.findByShortenedUrl(shortenedUrl);
        if (urlMappingOptional.isPresent()) {
            Url urlMapping = urlMappingOptional.get();
            Url updatedObject = incrementAccessCount(urlMapping);
            return updatedObject.getOriginalUrl();
        } else {
            throw new NotFoundException("shortened url : " + shortenedUrl + "does not exist");
        }
    }

    public Url incrementAccessCount(Url url) {
        url.setAccessCount(url.getAccessCount() + 1);
        return urlRepository.save(url);
    }


    public UrlStats getUrlStats(String shortUrl) throws NotFoundException {
        Optional<Url> url = urlRepository.findByShortenedUrl(shortUrl);
        if (url.isPresent()) {
            Url urlMapping = url.get();
            return new UrlStats(urlMapping.getShorteningCount(), urlMapping.getAccessCount());
        } else {
            throw new NotFoundException("stats for short url :" + shortUrl + "does not exist");
        }
    }

    public List<Url> getAllUrls() {
        return urlRepository.findAll();
    }
}