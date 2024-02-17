package com.testo.shortify.repository;

import com.testo.shortify.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findByOriginalUrl(String originalUrl);
    Optional<Url> findByShortenedUrl(String shortenedUrl);

}
