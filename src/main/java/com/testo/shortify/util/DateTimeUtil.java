package com.testo.shortify.util;

import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Log4j2
public class DateTimeUtil {

    public static Long epochTimeNow() {
        return LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
    }
}